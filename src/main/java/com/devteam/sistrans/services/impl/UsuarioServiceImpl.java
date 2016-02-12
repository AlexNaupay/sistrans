package com.devteam.sistrans.services.impl;

import com.devteam.sistrans.dto.SistransDto;
import com.devteam.sistrans.entities.Usuario;
import com.devteam.sistrans.repositories.UsuarioDao;
import com.devteam.sistrans.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public void login(String usuario, String password, String codigoSistema) {

    }

    @Override
    public SistransDto obtenerUsuario(String username) {
        SistransDto usuarioDto = new SistransDto();
        try{
            Usuario usuario = usuarioDao.obtenerUsuario(username);
            usuarioDto.setData(usuario);
            if (usuario == null){
                usuarioDto.setErrorCod(1);
                usuarioDto.setErrorDesc("Usuario no encontrado");
            }
        }catch (DataAccessResourceFailureException darfe){
            usuarioDto.setErrorCod(2);
            usuarioDto.setErrorDesc("Error de conexión en la base de datos");
        }catch (DataAccessException dae){
            usuarioDto.setErrorCod(3);
            usuarioDto.setErrorDesc("Error desconocido en la base de datos");
        }
       return usuarioDto;
    }
}
