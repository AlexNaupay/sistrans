package com.devteam.sistrans.services.impl;

import com.devteam.sistrans.dto.SistransDto;
import com.devteam.sistrans.entities.Opcion;
import com.devteam.sistrans.entities.Usuario;
import com.devteam.sistrans.repositories.OpcionDao;
import com.devteam.sistrans.repositories.UsuarioDao;
import com.devteam.sistrans.services.ControlAccesoService;
import com.devteam.sistrans.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("controlAcceso")
public class ControlAccesoImpl implements ControlAccesoService {

    @Autowired
    UsuarioDao usuarioDao;

    @Autowired
    OpcionDao opcionDao;

    /**
     * Autentica al usuario, retorna sus opciones en el sistema en caso de exito
     * @param username Nombre de usuario
     * @param password Contraseña recibida
     * @param sistema Id del siatema dónde hace login
     * @return error_cod, error_desc y opciones del usuario
     *       0 Inició sesión correctamente
     *       1 Usuario no registrado
     *       2 Tamaño de contraseña invalido
     *       3 Contraseña incorrecta
     *       4 Usuario bloqueado
     *
     */
    public SistransDto login(String username, String password, String sistema) {
        SistransDto loginDto =  new SistransDto();
        List<Opcion> opciones = new ArrayList();

        try{
            Usuario usuario = usuarioDao.obtenerUsuario(username); //Obtención del usuario
            if (usuario == null){
                loginDto.setErrorCod(1);
                loginDto.setErrorDesc("Usuario no registrado");
            }else if (password.length() <5 || password.length()>50){
                loginDto.setErrorCod(2);
                loginDto.setErrorDesc("Tamaño de contraseña invalido");
            }else if (!password.equals(usuario.getPassword())){
                loginDto.setErrorCod(3);
                loginDto.setErrorDesc("Contraseña incorrecta");
            }else if (usuario.getEstado() == 2){ //Estado de bloqueado ...
                loginDto.setErrorCod(4);
                loginDto.setErrorDesc("Usuario Bloqueado");
            }else { //Estamos en el caso correcto
                opciones = opcionDao.opcionesPorUsuario(username, sistema); //Las opciones del usuario
                loginDto.setData(opciones);
            }
        }catch (DataAccessResourceFailureException darfe){
            loginDto.setErrorCod(5);
            loginDto.setErrorDesc("Error de conexión a la base de datos");
        }

        return loginDto;
    }
}
