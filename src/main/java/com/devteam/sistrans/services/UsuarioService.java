package com.devteam.sistrans.services;

import com.devteam.sistrans.dto.SistransDto;
/**
 * @author alexh
 */
public interface UsuarioService {
    void login(String usuario, String password, String codigoSistema);
    SistransDto obtenerUsuario(String username);
}
