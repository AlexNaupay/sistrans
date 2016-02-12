package com.devteam.sistrans.repositories;

import com.devteam.sistrans.dto.SistransDto;
import com.devteam.sistrans.entities.Transaccion;

import java.util.List;

public interface ArchivoDao {


    /**
     * Guarda una transaccion en la BD
     * @param transaccion : La transaccion a guardar
     * @return La transaccion con los campos completos
     */
    Transaccion guardarTransacion(Transaccion transaccion);

    /**
     * Guarda las transacciones en la BD
     * @param transaccions : Las transacciones
     * @return sistrandto
     */
    SistransDto guardarTransaciones(List<Transaccion> transaccions);
}
