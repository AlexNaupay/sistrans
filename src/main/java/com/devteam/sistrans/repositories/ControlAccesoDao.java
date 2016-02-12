package com.devteam.sistrans.repositories;

public interface ControlAccesoDao {
    void obtenerPerfiles(String usuario);

    void opcionesPorUsuario(String usuario);

    void opcionesPorPerfil(String codigoPerfil);

    void login(String usuario, String password, String codigoSistema);

}
