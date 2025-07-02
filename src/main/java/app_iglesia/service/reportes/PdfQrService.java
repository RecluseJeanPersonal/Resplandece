package app_iglesia.service.reportes;

import app_iglesia.entity.Entrada;
import app_iglesia.payload.request.PdfQrRequest;
import app_iglesia.repository.Entrada.EntradaRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.List;
import java.util.UUID;

@Service
public class PdfQrService {

    private final EntradaRepository entradaRepository;

    public PdfQrService(EntradaRepository entradaRepository) {
        this.entradaRepository = entradaRepository;
    }

    public byte[] generarPdfConIds(List<UUID> ids) throws Exception {
        List<Entrada> entradas = entradaRepository.findAllById(ids);

        List<PdfQrRequest> codigosYnombres = entradas.stream()
                .map(e -> {
                    PdfQrRequest dto = new PdfQrRequest();
                    dto.setCodigoQr(e.getCodigoQR());
                    dto.setNombreEntrada(e.getNombre());
                    return dto;
                })
                .toList();

        return generarPdfConVariosQr(codigosYnombres);
    }

    public byte[] generarPdfConVariosQr(List<PdfQrRequest> codigosYnombres) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Document document = new Document();

        PdfWriter writer = PdfWriter.getInstance(document, baos);
        document.open();

        // Leer el PDF base como plantilla
        PdfReader plantilla = new PdfReader(new FileInputStream("src/main/resources/Entrada.pdf"));
        Rectangle pageSize = plantilla.getPageSize(1);

        int qrSize = 145;

        for (PdfQrRequest item : codigosYnombres) {
            document.setPageSize(pageSize);
            document.newPage();

            // Importar página de la plantilla
            PdfContentByte cb = writer.getDirectContent();
            PdfImportedPage page = writer.getImportedPage(plantilla, 1);
            cb.addTemplate(page, 0, 0);

            // Generar QR
            BitMatrix bitMatrix = new MultiFormatWriter().encode(
                    item.getCodigoQr().toString(), BarcodeFormat.QR_CODE, qrSize, qrSize);
            BufferedImage qrImage = new BufferedImage(qrSize, qrSize, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < qrSize; x++) {
                for (int y = 0; y < qrSize; y++) {
                    qrImage.setRGB(x, y, bitMatrix.get(x, y) ? 0x000000 : 0xFFFFFF);
                }
            }

            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            ImageIO.write(qrImage, "PNG", pngOutputStream);
            Image qrItextImage = Image.getInstance(pngOutputStream.toByteArray());

            // Calcular posición centrada (franja 2/4)
            float pageWidth = pageSize.getWidth();
            float pageHeight = pageSize.getHeight();

            float qrX = (pageWidth - qrSize) / 2;
            float franjaTop = pageHeight * 0.75f;
            float franjaBottom = pageHeight * 0.5f;
            float franjaHeight = franjaTop - franjaBottom;
            float qrY = franjaBottom + (franjaHeight - qrSize) / 2 + 10;

            qrItextImage.setAbsolutePosition(qrX, qrY);
            qrItextImage.scaleAbsolute(qrSize, qrSize);
            cb.addImage(qrItextImage);
        }

        document.close();
        plantilla.close();

        return baos.toByteArray();
    }
}
