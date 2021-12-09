package com.edu.fiis.assetecback.dao;

import com.edu.fiis.assetecback.dao.AssetecDao;
import com.edu.fiis.assetecback.dao.AssetecDao2;
import com.edu.fiis.assetecback.dto.*;
import com.edu.fiis.assetecback.dto.request.RegistroAsistencia;
import com.edu.fiis.assetecback.dto.request.TrabajadorActividad;
import com.edu.fiis.assetecback.dto.responses.ResumenTrabajador;
import com.edu.fiis.assetecback.dto.responses.Rol;
import com.edu.fiis.assetecback.dto.responses.SolicitudInterno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AssetecDaoImpl2 implements AssetecDao2 {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<SolicitudInterno> traerSolicitudesInterno(String apellidosNombres){
        List<SolicitudInterno> Lista = new ArrayList<>();
        String sql =  "SELECT " +
                "S.COD_SOLICITUD, S.FECHA_SOLICITUD, N.APELLIDOSNOMBRES, C.CORREO " +
                "FROM SOLICITUD S, PERSONA P, CORREO C, " +
                "( SELECT DNI, CONCAT( INITCAP(APELLIDO_P), ' ', INITCAP(APELLIDO_M), ' ',INITCAP(PRIMER_NOMBRE)) AS APELLIDOSNOMBRES FROM PERSONA) N " +
                "WHERE P.DNI = N.DNI AND " +
                "P.DNI = S.DNI AND " +
                "P.DNI=C.DNI AND " +
                "S.COD_TIPO = 'I' AND" +
                "UPPER(N.APELLIDOSNOMBRES) = UPPER(%?%) AND " +
                "(S.ESTADO = 'Pendiente') " + "ORDER BY T.TIPO DESC, S.FECHA_SOLICITUD";
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, apellidosNombres);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                SolicitudInterno elemento = new SolicitudInterno();
                elemento.setCodSolicitud(rs.getInt("COD_SOLICITUD"));
                elemento.setFechaSolicitud(rs.getString("FECHA_SOLICITUD"));
                elemento.setApellidosNombres(rs.getString("APELLIDOSNOMBRES"));
                elemento.setCorreo(rs.getString("CORREO"));
                Lista.add(elemento);
            }
            rs.close();
            ps.close();
            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Lista;
    }

}
