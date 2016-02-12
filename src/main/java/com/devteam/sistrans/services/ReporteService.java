package com.devteam.sistrans.services;

import com.devteam.sistrans.dto.SistransDto;

public interface ReporteService {
    SistransDto obtenerAutorizadores();
    SistransDto obtenerAdquirientes();
    SistransDto obtenerCanales();


    SistransDto reporteAdquirienteCanal(String fechaPrev, String fechaNext, String adqFilters, String canalFilters);
    SistransDto reportePorAutorizador(String fechaPrev, String fechaNext, String autFilters);
    SistransDto reporteDetallado(String fechaPrev, String fechaNext, String autFilters, String adqFilters, String canalFilters);
}
