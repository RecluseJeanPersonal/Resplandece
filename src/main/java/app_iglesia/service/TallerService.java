package app_iglesia.service;

import app_iglesia.entity.Taller;

import java.util.List;
import java.util.UUID;

public interface TallerService {
    Taller crearTaller(Taller taller);
    Taller editarTaller(UUID id, Taller taller);
    List<Taller> listarTalleres();
}