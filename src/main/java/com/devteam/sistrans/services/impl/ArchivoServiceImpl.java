package com.devteam.sistrans.services.impl;

import com.devteam.sistrans.dto.SistransDto;
import com.devteam.sistrans.entities.Campo;
import com.devteam.sistrans.entities.Transaccion;
import com.devteam.sistrans.exceptions.*;
import com.devteam.sistrans.repositories.ArchivoDao;
import com.devteam.sistrans.repositories.PlantillaDao;
import com.devteam.sistrans.services.ArchivoService;
import com.devteam.sistrans.utils.SistransUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
public class ArchivoServiceImpl implements ArchivoService {

    private static final long MAX_UPLOAD_SIZE_DEFAULT = 104857600; //100MB

    @Resource(name = "config")
    Properties properties;

    @Autowired
    PlantillaDao plantillaDao;

    @Autowired
    ArchivoDao archivoDao;

    private static Log logger = LogFactory.getLog(ArchivoServiceImpl.class);

    @Override
    public SistransDto procesarArchivo(MultipartFile archivo) {
        SistransDto fileDto = new SistransDto();
        long tamanoMaximo = Long.parseLong(properties.getProperty("file.max.upload.size", ""+MAX_UPLOAD_SIZE_DEFAULT));

        if (!archivo.getContentType().equals("text/plain")){
            fileDto.setErrorCod(1);
            fileDto.setErrorDesc("El archivo no es de tipo plano");
        }else if (archivo.isEmpty()){
            fileDto.setErrorCod(2);
            fileDto.setErrorDesc("El archivo no es de tipo plano");
        }else if (archivo.getSize() > tamanoMaximo){
            fileDto.setErrorCod(3);
            fileDto.setErrorDesc("El archivo supera el tamaño máximo: "+(tamanoMaximo/1024)+"MB");
        }else { // Se empieza el procesamiento de las filas del archivo
            try {
                fileDto.setErrorDesc("Carga de archivo correcto");

                SistransDto dataDto = procesarFilasArchivo(archivo);
                fileDto.setData(dataDto);
                if (dataDto.getErrorCod() == 0){
                    logger.info("==== Todas las filas correctas, guardar en DB ===");
                    guardarTransacciones(dataDto);
                }
            }catch (DataAccessException dae){
                logger.fatal("Error al guardar en la BD");
                fileDto.setErrorCod(4);
                fileDto.setErrorDesc("Error al guardar en la BD");
            }
        }

        return fileDto;
    }

    public boolean guardarTransacciones(SistransDto dataDto) throws DataAccessException{
        List<SistransDto> rows = (List<SistransDto>) dataDto.getData();
        rows.forEach(row ->archivoDao.guardarTransacion((Transaccion) row.getData()));
        return true;
    }

    public SistransDto procesarFilasArchivo(MultipartFile archivo) {
        SistransDto dataDto = new SistransDto();
        List<SistransDto> rows = new ArrayList<>(); // Las filas procesadas
        SistransDto rowDto;
        int  rowSizeExpected = 0;
        boolean isOk = true; // Si todas las filas son correctas

        List<Campo> campos = plantillaDao.obtenerCampos(); //La estrcutura de plantillas

        if (campos.size()>0)
        for(Campo campo: campos){
            rowSizeExpected+=campo.getLongitud();
        }

        //**** Que pasa si no hay campos en la BD? Que pasa si no hay 7?
        // Obtenr la plantilla y corrrecr algoritmo
        String line;
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(archivo.getInputStream()));
            while ((line = bufferedReader.readLine()) != null){
                rowDto = procesarFila(line,campos,rowSizeExpected);
                rows.add(rowDto);
                if (rowDto.getErrorCod() != 0 && isOk)
                    isOk = false;
                //System.out.println(transaccion);
            }
            if (isOk){
                dataDto.setErrorDesc("Todas las filas sintacticamnete son correctas");
            }else {
                dataDto.setErrorCod(1);
                dataDto.setErrorDesc("Algunas filas son incorrectas sintacticamente");
            }
            dataDto.setData(rows);
        } catch (ReflectionException e) {
            dataDto.setErrorCod(50);
            dataDto.setErrorDesc(e.getMessage());
            logger.fatal("La API reflection no se puede ejecutar, compruebe el id de " +
                    "los campos en db y el nombre del atributo en la clase Transaccion");
        }catch (IOException e) {
            e.printStackTrace();
        }

        return dataDto;
    }

    /**
     * Mapea una fila a su correspondiente campo de la clase
     * @param line
     * @param campos
     * @param rowSizeExpected
     */
    public SistransDto procesarFila(String line, List<Campo> campos, int rowSizeExpected) throws ReflectionException {
        SistransDto rowDto = new SistransDto();

        String codigoCampo; //Codigo del campo actual
        Transaccion transaccion = new Transaccion(); //El objeto transación
        int position = 0; //Posición del campo en la linea
        String fieldValue; //Valor en el campo actual
        Field field; //El campo actual
        transaccion.setRaw(line); //Raw Data
        try {
            if (StringUtils.isEmpty(line))
                throw new EmptyRowException("La fila está vacía");

            if (line.length() != rowSizeExpected)
                throw new IncorrectRowSizeException("La fila tiene un tamaño incorrecto: "
                        +"Tamaño esperado: "+rowSizeExpected+", recibido: "+line.length());

            for (Campo campo : campos) {
                codigoCampo = campo.getCodigo();
                fieldValue = line.substring(position,position+campo.getLongitud()); //El valor del campo en la linea entrante

                if( !SistransUtils.syntaxValidate(fieldValue, campo.getTipo())) //Si el campo no cumple con su tipo
                    throw new FieldSyntaxException("Error de Sintaxis en el campo "
                            +campo.getNombre()+"; entre columna: "+(position+1)+" y "+(position+campo.getLongitud()));

                //Setear el valor en el objeto transacion
                field = transaccion.getClass().getDeclaredField(codigoCampo);
                field.setAccessible(true);
                field.set(transaccion,fieldValue);
                position += campo.getLongitud();
            }

            rowDto.setErrorDesc("Fila correctamente procesada");
            rowDto.setData(transaccion);
            return rowDto;
        }catch (NoSuchFieldException | IllegalAccessException e) {
            String msg = "Error fatal, La API reflection no se puede Ejecutar ...";
            logger.fatal(msg);
            throw new ReflectionException(msg);
        } catch (EmptyRowException ere) {
            rowDto.setErrorCod(101);
            rowDto.setErrorDesc(ere.getMessage());
            rowDto.setData(transaccion);
            logger.error(ere.getMessage());
            return rowDto;
        }catch (IncorrectRowSizeException i) {
            rowDto.setErrorCod(102);
            rowDto.setErrorDesc(i.getMessage());
            logger.error(i.getMessage());
            rowDto.setData(transaccion);
            return rowDto;
        }catch (FieldSyntaxException fse) {
            rowDto.setErrorCod(103);
            rowDto.setErrorDesc(fse.getMessage());
            logger.error(fse.getMessage());
            rowDto.setData(transaccion);
            return rowDto;
        }
    }

}
