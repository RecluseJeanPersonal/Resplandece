package app_iglesia.service.reportes;

import app_iglesia.entity.Entrada;
import app_iglesia.payload.request.PdfQrRequest;
import app_iglesia.repository.Entrada.EntradaRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.io.image.ImageDataFactory;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.UUID;

@Service
public class PdfQrService {

    private final EntradaRepository entradaRepository;

    public PdfQrService(EntradaRepository entradaRepository) {
        this.entradaRepository = entradaRepository;
    }

    public byte[] generarPdfConIds(List<UUID> ids) throws Exception {
        // Buscar entradas completas con nombre y código QR
        List<Entrada> entradas = entradaRepository.findAllById(ids);

        // Mapear a DTO para la generación del PDF
        List<PdfQrRequest> codigosYnombres = entradas.stream()
                .map(e -> {
                    PdfQrRequest dto = new PdfQrRequest();
                    dto.setCodigoQr(e.getCodigoQR());  // Entrada debe tener este método
                    dto.setNombreEntrada(e.getNombre());
                    return dto;
                })
                .toList();

        // Generar PDF con los datos obtenidos
        return generarPdfConVariosQr(codigosYnombres);
    }

    public byte[] generarPdfConVariosQr(List<PdfQrRequest> codigosYnombres) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(baos);
        PdfDocument pdfDoc = new PdfDocument(writer);

        // Cargar el PDF base como plantilla
        PdfDocument plantilla = new PdfDocument(new PdfReader("src/main/resources/Entrada.pdf"));

        // Crear el Document una sola vez para todo pdfDoc
        Document document = new Document(pdfDoc);

        Integer qrSize = 145;

        for (PdfQrRequest item : codigosYnombres) {
            // Copiar la página plantilla al nuevo pdf
            plantilla.copyPagesTo(1, 1, pdfDoc);

            // Obtener la última página agregada
            PdfPage pagina = pdfDoc.getLastPage();
            PageSize pageSize = new PageSize(pagina.getPageSize());

            float pageWidth = pageSize.getWidth();
            float pageHeight = pageSize.getHeight();

            // Calcular posición para centrar QR en la franja 2/4
            float qrX = (pageWidth - qrSize) / 2; // centrado horizontal
            float franjaTop = pageHeight * 0.75f;
            float franjaBottom = pageHeight * 0.5f;
            float franjaHeight = franjaTop - franjaBottom;
            float qrY = franjaBottom + (franjaHeight - qrSize) / 2 + 10; // posicion vertical

            // Generar QR
            BitMatrix bitMatrix = new MultiFormatWriter().encode(
                    item.getCodigoQr().toString(), BarcodeFormat.QR_CODE, qrSize, qrSize);
            BufferedImage qrImage = new BufferedImage(qrSize, qrSize, BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < qrSize; x++) {
                for (int y = 0; y < qrSize; y++) {
                    qrImage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0x00000000);
                }
            }
            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            ImageIO.write(qrImage, "PNG", pngOutputStream);
            byte[] pngData = pngOutputStream.toByteArray();

            Image qrItextImage = new Image(ImageDataFactory.create(pngData))
                    .setWidth(qrSize)
                    .setHeight(qrSize)
                    .setFixedPosition(pdfDoc.getPageNumber(pagina), qrX, qrY);// importante asignar la página

            document.add(qrItextImage);
        }

        plantilla.close();
        document.close();
        pdfDoc.close();

        return baos.toByteArray();
    }

}