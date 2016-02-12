package com.devteam.sistrans.services.impl;

import com.devteam.sistrans.dto.SistransDto;
import com.devteam.sistrans.entities.Campo;
import com.devteam.sistrans.repositories.PlantillaDao;
import com.devteam.sistrans.services.PlantillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlantillaServiceImpl implements PlantillaService{
    @Autowired
    PlantillaDao plantillaDao;

    @Override
    public SistransDto actualizarCampos(List<Campo> campos) {
        SistransDto dataDto = new SistransDto();
        List<SistransDto> fieldsDto = new ArrayList<>();
        boolean isOkAll = true; dataDto.setErrorDesc("Actualización correcta");

        for(Campo campo : campos){
            SistransDto dto = validarCampo(campo);
            if (dto.getErrorCod() != 0 && isOkAll){
                dataDto.setErrorCod(1);
                dataDto.setErrorDesc("Algunos errores al actualizar");
                isOkAll = false;
            }
            fieldsDto.add(dto);
        }

        if (isOkAll) campos.forEach(this::actualizarCampo);

        dataDto.setData(fieldsDto);
        return dataDto;
    }


    public SistransDto actualizarCampo(Campo campo){
        SistransDto sistransDto = validarCampo(campo);
        if (sistransDto.getErrorCod() == 0){
            sistransDto.setData(plantillaDao.actualizarCampo((Campo) sistransDto.getData()));
        }

        return sistransDto;
    }


    private SistransDto validarCampo(Campo campo){
        SistransDto fieldDto = new SistransDto();
        SistransDto fieldDbDto = obtenerCampo(campo.getCodigo());

        try{
            if (fieldDbDto.getErrorCod() == 0){
                Campo campodb = (Campo)fieldDbDto.getData();
                if ( (campo.getLongitud()<campodb.getLongitudMinama() || campo.getLongitud()>campodb.getLongitudMaxima()) ){
                    fieldDto.setErrorCod(1);
                    fieldDto.setErrorDesc("El tamaño debe estar entre: " + campodb.getLongitudMinama() + " y "
                            + campodb.getLongitudMaxima() + " inclusive");
                    fieldDto.setData(campodb);
                }else {
                    fieldDto.setErrorDesc("Cambio correcto");
                    fieldDto.setData(campo);
                }



            }else {
                fieldDto.setErrorCod(fieldDbDto.getErrorCod());
                fieldDto.setErrorDesc(fieldDbDto.getErrorDesc());

            }

            return fieldDto;

        }catch (EmptyResultDataAccessException e){
            fieldDto.setErrorCod(4);
            fieldDto.setErrorDesc("Campo no encontrado en la BD");
            fieldDto.setData(campo);
            return fieldDto;
        }catch (DataAccessException dae){
            fieldDto.setErrorCod(2);
            fieldDto.setErrorDesc("Error en la Base de datos");
            return fieldDto;
        }catch (Exception e){
            fieldDto.setErrorCod(3);
            fieldDto.setErrorDesc("Error desconocido");
            return fieldDto;
        }
    }


    @Override
    public SistransDto obtenerCampos() {
        SistransDto fieldsDto = new SistransDto();
        try{
            List<Campo> campos = plantillaDao.obtenerCampos();
            fieldsDto.setErrorCod(0); fieldsDto.setErrorDesc("Todo correcto");
            fieldsDto.setData(campos);
            return  fieldsDto;
        }catch (DataAccessException dae){
            fieldsDto.setErrorCod(1); fieldsDto.setErrorDesc("Error en la Base de Datos");
            return  fieldsDto;
        }

    }

    @Override
    public SistransDto obtenerCampo(String codigoCampo) {
        SistransDto sistransDto = new SistransDto();
        Campo campo;
        try {
            campo = plantillaDao.obtenerCampo(codigoCampo);
            sistransDto.setData(campo);
        }catch (DataAccessException dae){
            sistransDto.setErrorCod(1);
            sistransDto.setErrorDesc("No se pudo obtener el campo");
        }
        return sistransDto;
    }

}
