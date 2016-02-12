package com.devteam.sistrans.services.impl;

import com.devteam.sistrans.dto.SistransDto;
import com.devteam.sistrans.repositories.ReporteDao;
import com.devteam.sistrans.services.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


@Service
public class ReporteServiceImpl implements ReporteService{

    @Autowired
    ReporteDao reporteDao;

    @Override
    public SistransDto obtenerAutorizadores() {
        SistransDto sistransDto = new SistransDto();
        try{
            sistransDto.setData(reporteDao.obtenerAutorizadores());
            return  sistransDto;
        }catch (DataAccessException dae){
            sistransDto.setErrorCod(1);
            sistransDto.setErrorDesc("Error en la BD");
            return sistransDto;
        }
    }

    @Override
    public SistransDto obtenerAdquirientes() {
        SistransDto sistransDto = new SistransDto();
        try{
            sistransDto.setData(reporteDao.obtenerAdquirientes());
            return  sistransDto;
        }catch (DataAccessException dae){
            sistransDto.setErrorCod(1);
            sistransDto.setErrorDesc("Error en la BD");
            return sistransDto;
        }
    }

    @Override
    public SistransDto obtenerCanales() {
        SistransDto sistransDto = new SistransDto();
        try{
            sistransDto.setData(reporteDao.obtenerCanales());
            return  sistransDto;
        }catch (DataAccessException dae){
            sistransDto.setErrorCod(1);
            sistransDto.setErrorDesc("Error en la BD");
            return sistransDto;
        }
    }

    @Override
    public SistransDto reporteAdquirienteCanal(String fechaPrev, String fechaNext,String adqFilters, String canalFilters) {
        SistransDto sistransDto = new SistransDto();
        try{if (fechaPrev!=null && fechaNext!=null){
            sistransDto.setData(reporteDao.reporteAdquirienetCanal(fechaPrev,fechaNext,adqFilters,canalFilters));
        }else {
            sistransDto.setErrorCod(2);
            sistransDto.setErrorDesc("Las fechas deben ser no nulas");
        }
            return  sistransDto;

        }catch (DataAccessException dae){
            sistransDto.setErrorCod(1);
            sistransDto.setErrorDesc("Error en la BD");
            //System.out.println(dae.getMessage());
            return sistransDto;
        }
    }

    @Override
    public SistransDto reportePorAutorizador(String fechaPrev, String fechaNext, String autFilters) {
        SistransDto sistransDto = new SistransDto();
        try{if (fechaPrev!=null && fechaNext!=null){
                sistransDto.setData(reporteDao.reporteAutorizador(fechaPrev,fechaNext,autFilters));
            }else {
                sistransDto.setErrorCod(2);
                sistransDto.setErrorDesc("Las fechas deben ser no nulas");
            }
            return  sistransDto;

        }catch (DataAccessException dae){
            sistransDto.setErrorCod(1);
            sistransDto.setErrorDesc("Error en la BD");
            //System.out.println(dae.getMessage());
            return sistransDto;
        }
    }

    @Override
    public SistransDto reporteDetallado(String fechaPrev, String fechaNext,String autFilters ,String adqFilters, String canalFilters) {
        SistransDto sistransDto = new SistransDto();
        try{if (fechaPrev!=null && fechaNext!=null){
            sistransDto.setData(reporteDao.reporteDetallado(fechaPrev, fechaNext, autFilters, adqFilters, canalFilters));
        }else {
            sistransDto.setErrorCod(2);
            sistransDto.setErrorDesc("Las fechas deben ser no nulas");
        }
            return  sistransDto;

        }catch (DataAccessException dae){
            sistransDto.setErrorCod(1);
            sistransDto.setErrorDesc("Error en la BD");
            //System.out.println(dae.getMessage());
            return sistransDto;
        }
    }
}
