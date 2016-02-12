package com.devteam.sistrans.repositories;


import com.devteam.sistrans.entities.Opcion;

import java.util.List;

public interface OpcionDao {
    List<Opcion> opcionesPorUsuario(String username, String sistema);
}
