package com.devteam.sistrans.services;

import com.devteam.sistrans.dto.SistransDto;

public interface ControlAccesoService {
    /*void obtenerPerfiles(String usuario);

    void opcionesPorUsuario(String usuario);

    void opcionesPorPerfil(String codigoPerfil);*/

    SistransDto login(String username, String password, String sistema);

}
