package com.devteam.sistrans.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author alexh
 */
@Repository
public class LoginDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;


    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource); //Iniciar el template
    }

    public void findAll(){
        System.out.println("Por aqui ..");
        PreparedStatement ps;
        Connection con = null;
        ResultSet datos;


        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement("SELECT * FROM tp_campo;");

            datos = ps.executeQuery();
            while (datos.next()) {
                System.out.println(datos.getString("NOMBRE_CAMPO"));
            }

        } catch (SQLException sqle) {
            throw new RuntimeException(sqle);
        } //cerrar la conexión si es que está abierta
        finally {
            try {
                System.out.println("cerrando ....");
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
