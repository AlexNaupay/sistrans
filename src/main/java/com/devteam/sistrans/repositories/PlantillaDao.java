package com.devteam.sistrans.repositories;

import com.devteam.sistrans.entities.Campo;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @author alexh
 */
public interface PlantillaDao {


    Campo actualizarCampo(Campo campo);

    List<Campo> obtenerCampos() throws DataAccessException;

    Campo obtenerCampo(String codigoCampo);
}
