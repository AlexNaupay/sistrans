package com.devteam.sistrans.repositories.impl;

import com.devteam.sistrans.entities.Usuario;
import com.devteam.sistrans.repositories.UsuarioDao;
import com.devteam.sistrans.repositories.mappers.UsuarioRowMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository("usuarioDao")
public class UsuarioDaoImpl implements UsuarioDao{

    private DataSource dataSource;
    private SimpleJdbcCall simpleJdbcCall;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;

    }

    @Override
    public Usuario obtenerUsuario(String username) throws DataAccessException {
        simpleJdbcCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("SP_OBTENER_USUARIO")
                .returningResultSet("users", new UsuarioRowMapper());

        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("in_username", username);

        Map map = simpleJdbcCall.execute(in);

        List list = (List) map.get("users");
        if (list.size() == 0)
            return null;
        return (Usuario)list.get(0);

    }

    /*public Usuario obtenerUsuario(String usuario){
        System.out.println("Por aqui ...");
        PreparedStatement ps;
        Connection con = null;
        ResultSet datos;


        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement("SELECT * FROM tp_usuario WHERE COD_USUARIO=?");
            ps.setString(1,usuario);

            datos = ps.executeQuery();
            while (datos.next()) {
                System.out.println(datos.getString("NOMBRE_USUARIO"));
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
        return null;

    }*/
}

