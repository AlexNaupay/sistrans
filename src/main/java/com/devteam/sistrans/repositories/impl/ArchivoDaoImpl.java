package com.devteam.sistrans.repositories.impl;

import com.devteam.sistrans.dto.SistransDto;
import com.devteam.sistrans.entities.Transaccion;
import com.devteam.sistrans.repositories.ArchivoDao;
import com.devteam.sistrans.utils.SistransUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;


@Repository
public class ArchivoDaoImpl implements ArchivoDao{

    private DataSource dataSource;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public Transaccion guardarTransacion(Transaccion transaccion) throws DataAccessException{
        long id;
        // Output format date and time ..
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(dataSource)
                .withFunctionName("USP_GUARDAR_TRANSACCION_FUC");
        SqlParameterSource sqlParameterSourceIn = new MapSqlParameterSource()
                .addValue("IN_FECHA", SistransUtils.dateToDbFormat(transaccion.getFecha())) //Pasar a formato de DB
                .addValue("IN_HORA", SistransUtils.hourToDbFormat(transaccion.getHora())) //Pasar a formato de DB
                .addValue("IN_TARJETA", transaccion.getTarjeta())
                .addValue("IN_MONTO",transaccion.getMonto())
                .addValue("IN_CANAL",transaccion.getCanal())
                .addValue("IN_ADQUIRIENTE",transaccion.getAdquiriente())
                .addValue("IN_AUTORIZADOR", transaccion.getAutorizador());

        id = simpleJdbcCall.executeFunction(Long.class, sqlParameterSourceIn);
        transaccion.setCodigo(id);
        return transaccion;
    }

    @Override
    public SistransDto guardarTransaciones(List<Transaccion> transaccions) {
        SistransDto sistransDto = new SistransDto();
        try {
            transaccions.forEach(this::guardarTransacion); //Todo está por referencia?
            sistransDto.setErrorCod(0);
            sistransDto.setErrorDesc("Todas las filas correctamente guardadas");
            sistransDto.setData(transaccions);
            return sistransDto;
        }catch (DataAccessException dae){
            sistransDto.setErrorCod(1);
            sistransDto.setErrorDesc("Errores al guardar las fila");
            sistransDto.setData(transaccions);
            return sistransDto;
        }
    }
}
