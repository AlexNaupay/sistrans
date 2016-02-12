package com.devteam.sistrans.repositories;

import com.devteam.sistrans.entities.Usuario;
import org.springframework.dao.DataAccessException;

/**
 * @author alexh
 */

public interface UsuarioDao {
    Usuario obtenerUsuario(String username) throws DataAccessException;

}
