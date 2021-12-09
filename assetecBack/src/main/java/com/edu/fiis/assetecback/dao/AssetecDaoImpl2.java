package com.edu.fiis.assetecback.dao;

import com.edu.fiis.assetecback.dto.Contacto;
import com.edu.fiis.assetecback.dto.Solicitud;
import com.edu.fiis.assetecback.dto.Trabajador;
import com.edu.fiis.assetecback.dto.request.Buscar;
import com.edu.fiis.assetecback.dto.request.SolicitudRespuesta;
import com.edu.fiis.assetecback.dto.responses.SolicitudExterno;
import com.edu.fiis.assetecback.dto.responses.SolicitudInterno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AssetecDaoImpl2 implements AssetecDao2 {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<SolicitudInterno> traerSolicitudesInterno(Buscar buscar){
        List<SolicitudInterno> Lista = new ArrayList<>();

        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String sql =  " SELECT " +
                    " S.COD_SOLICITUD, S.FECHA_SOLICITUD, N.APELLIDOSNOMBRES, C.CORREO " +
                    " FROM SOLICITUD S, PERSONA P, CORREO C, " +
                    " ( SELECT DNI, CONCAT( INITCAP(APELLIDO_P), ' ', INITCAP(APELLIDO_M), ' ',INITCAP(PRIMER_NOMBRE)) AS APELLIDOSNOMBRES FROM PERSONA) N " +
                    " WHERE P.DNI = N.DNI AND " +
                    " P.DNI = S.DNI AND " +
                    " P.DNI=C.DNI AND " +
                    " S.COD_TIPO = 'I' AND " +
                    " UPPER(N.APELLIDOSNOMBRES) = ? AND " +
                    " (S.ESTADO = 'Pendiente') " + "ORDER BY T.TIPO DESC, S.FECHA_SOLICITUD ";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, "%"+ buscar.getContenido().toUpperCase() +"%");
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

    public List<SolicitudExterno> traerSolicitudesExterno(Buscar buscar){
        List<SolicitudExterno> Lista = new ArrayList<>();


        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String sql =  "SELECT "+
                    " S.COD_SOLICITUD, S.FECHA_SOLICITUD, N.APELLIDOSNOMBRES, CON.RUC, C.CORREO "+
                    " FROM SOLICITUD S, PERSONA P, CORREO C, CONTACTO CON "+
                    " ( SELECT DNI, CONCAT( INITCAP(APELLIDO_P), ' ', INITCAP(APELLIDO_M), ' ',INITCAP(PRIMER_NOMBRE)) AS APELLIDOSNOMBRES FROM PERSONA) N "+
                    " WHERE P.DNI = N.DNI AND "+
                    " P.DNI = S.DNI AND "+
                    " P.DNI=C.DNI AND "+
                    " P.DNI=CON.DNI "+
                    " S.COD_TIPO = 'E' AND "+
                    " UPPER(N.APELLIDOSNOMBRES) = ? AND "+
                    " (S.ESTADO = 'Pendiente') "+
                    " ORDER BY T.TIPO DESC, S.FECHA_SOLICITUD ";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, "%"+ buscar.getContenido().toUpperCase() +"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                SolicitudExterno elemento = new SolicitudExterno();
                elemento.setCodSolicitud(rs.getInt("COD_SOLICITUD"));
                elemento.setFechaSolicitud(rs.getString("FECHA_SOLICITUD"));
                elemento.setApellidosNombres(rs.getString("APELLIDOSNOMBRES"));
                elemento.setRuc(rs.getString("RUC"));
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



    public void responderSolicitudInterno (SolicitudInterno solicitudInterno, SolicitudRespuesta solicitudRespuesta){
        Solicitud auxSolicitud = new Solicitud();
        auxSolicitud.setCodigoSolicitud(solicitudInterno.getCodSolicitud());
        auxSolicitud.setFechaSolicitud(solicitudInterno.getFechaSolicitud());
        auxSolicitud.setDni(traerDniSolicitud(auxSolicitud));
        auxSolicitud.setFechaSolicitud(solicitudInterno.getFechaSolicitud());


        Trabajador auxTrabajador = new Trabajador();
        auxTrabajador.setDni(auxSolicitud.getDni());

        responderSolicitud(solicitudRespuesta);
        if(solicitudRespuesta.getEstado().equals("Aceptado")){
            agregarTrabajador(auxTrabajador);
        }
    }
    public void responderSolicitudExterno (SolicitudExterno solicitudExterno, SolicitudRespuesta solicitudRespuesta){
        Solicitud auxSolicitud = new Solicitud();
        auxSolicitud.setCodigoSolicitud(solicitudExterno.getCodSolicitud());
        auxSolicitud.setFechaSolicitud(solicitudExterno.getFechaSolicitud());
        auxSolicitud.setDni(traerDniSolicitud(auxSolicitud));
        auxSolicitud.setFechaSolicitud(solicitudExterno.getFechaSolicitud());


        Contacto auxContacto = new Contacto();
        auxContacto.setDni(auxSolicitud.getDni());
        auxContacto.setRuc(solicitudExterno.getRuc());

        responderSolicitud(solicitudRespuesta);
        if(solicitudRespuesta.getEstado().equals("Rechazado")){
            eliminarContacto(auxContacto);
        }
    }






    private String traerDniSolicitud (Solicitud solicitud){
        String dni = "";
        try{
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String sql=" SELECT DNI FROM SOLICITUD " +
                    "WHERE COD_SOLICITUD=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, solicitud.getCodigoSolicitud());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                dni=rs.getString("DNI");
            }
            rs.close();
            ps.close();
            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return dni;
    }
    private void responderSolicitud (SolicitudRespuesta solicitudRespuesta){
        String SQL=" UPDATE SOLICITUD SET "+
                "FECHA_MODIFICACION = (SELECT CURRENT_DATE) , ESTADO=? " +
                "WHERE COD_SOLICITUD=?";
        try{
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1,solicitudRespuesta.getEstado());
            ps.setInt(2,solicitudRespuesta.getCodSolicitud());
            ps.executeUpdate();
            ps.close();
            con.commit();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private void agregarTrabajador (Trabajador trabajador){
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String SQL=" INSERT INTO TRABAJADOR(DNI) " +
                    " VALUES( ? ) ";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1,trabajador.getDni());
            ps.executeUpdate();
            ps.close();
            con.commit();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private void eliminarTrabajador (Trabajador trabajador){
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String SQL=" DELETE FROM TRABAJADOR " +
                    " WHERE DNI = ? ";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1,trabajador.getDni());
            ps.executeUpdate();
            ps.close();
            con.commit();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private void agregarContacto (Contacto contacto){
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String SQL=" INSERT INTO CONTACTO(DNI, RUC) " +
                    "VALUES(? , ?) ";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1,contacto.getDni());
            ps.setString(2,contacto.getRuc());
            ps.executeUpdate();
            ps.close();
            con.commit();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private void eliminarContacto (Contacto contacto){
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String SQL=" DELETE FROM CONTACTO " +
                    " WHERE DNI = ? ";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1,contacto.getDni());
            ps.executeUpdate();
            ps.close();
            con.commit();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


}
