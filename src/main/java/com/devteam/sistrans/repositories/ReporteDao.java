package com.devteam.sistrans.repositories;

import com.devteam.sistrans.entities.Adquiriente;
import com.devteam.sistrans.entities.Autorizador;
import com.devteam.sistrans.entities.Canal;
import com.devteam.sistrans.models.ReporteAdquirienteCanal;
import com.devteam.sistrans.models.ReporteAutorizador;
import com.devteam.sistrans.models.ReporteDetallado;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface ReporteDao {
    List<Autorizador> obtenerAutorizadores() throws DataAccessException;
    List<Adquiriente> obtenerAdquirientes() throws DataAccessException;
    List<Canal> obtenerCanales() throws DataAccessException;


    List<ReporteAdquirienteCanal> reporteAdquirienetCanal(String fechaPrev, String fechaNext, String adqFilters, String canalFilters) throws DataAccessException;
    List<ReporteAutorizador> reporteAutorizador(String fechaPrev, String fechaNext, String autFilters) throws DataAccessException;
    List<ReporteDetallado> reporteDetallado(String fechaPrev, String fechaNext, String autFilters, String adqFilters, String canalFilters) throws DataAccessException;
}
