package com.devteam.sistrans.services;

import com.devteam.sistrans.dto.SistransDto;
import com.devteam.sistrans.entities.Campo;

import java.util.List;

/**
 * @author alexh
 */
public interface PlantillaService {
    SistransDto actualizarCampos(List<Campo> campos);
    SistransDto obtenerCampos();
    SistransDto obtenerCampo(String CodigoCampo);
}
