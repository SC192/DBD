package com.edu.fiis.assetecback.dao;

import com.edu.fiis.assetecback.dto.*;
import com.edu.fiis.assetecback.dto.request.Buscar;
import com.edu.fiis.assetecback.dto.request.SolicitudRegistro;
import com.edu.fiis.assetecback.dto.request.SolicitudRespuesta;
import com.edu.fiis.assetecback.dto.responses.SolicitudExterno;
import com.edu.fiis.assetecback.dto.responses.SolicitudInterno;
import com.edu.fiis.assetecback.dto.responses.TrabajadorPerfilNombre;
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

    public List<SolicitudInterno> traerSolicitudesInterno(Buscar buscar){
        List<SolicitudInterno> lista = new ArrayList<>();

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
                lista.add(elemento);
            }
            rs.close();
            ps.close();
            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lista;
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

    public void enviarSolicitudRegistro (SolicitudRegistro solicitudRegistro){
        Buscar buscar = new Buscar();
        buscar.setContenido(solicitudRegistro.getDni());
        Direccion direccion = new Direccion(solicitudRegistro.getCodPostal(), solicitudRegistro.getDireccion(), solicitudRegistro.getUnidad(), solicitudRegistro.getDistrito(), solicitudRegistro.getProvincia(), solicitudRegistro.getPais());
        Persona persona = new Persona(solicitudRegistro.getDni(), solicitudRegistro.getNombres(), solicitudRegistro.getApellidoM(), solicitudRegistro.getApellidoP(), solicitudRegistro.getContrasenia(), solicitudRegistro.getFirma());
        Telefono telefono = new Telefono(solicitudRegistro.getCelular(), solicitudRegistro.getPrefijo(), solicitudRegistro.getDni());
        Correo correo = new Correo (solicitudRegistro.getCorreo(), solicitudRegistro.getDni());
        Contacto contacto = new Contacto(solicitudRegistro.getDni(), solicitudRegistro.getRuc());
        Solicitud solicitud = new Solicitud();
        solicitud.setCodigoTipo((identificarTipoSolicitud(solicitudRegistro)));
        if(verificarDNIenPersona(buscar) == true){
            /** Existe el DNI en persona*/
            if(verificarDNIadmitido(buscar) == false){
                /** No fue admitido anteriormente*/
                /** Ejecutamos sentencias de actualizar la base de datos*/
                modificarCorreo(correo);
                modificarTelefono(telefono);
                modificarPersona(persona);
                agregarSolicitud(solicitud);
                if(identificarTipoSolicitud(solicitudRegistro) == "E"){
                    /**Solicitud de tipo Externo */
                    agregarContacto(contacto);
                }
                else{
                    /**Solicitud de tipo Interno */
                }
            }

        }else{
            /** No existe el DNI en persona */
            /** Ejecutamos sentencias de insercion en la base de datos*/

            agregarCorreo(correo);
            agregarTelefono(telefono);
            agregarSolicitud(solicitud);
            agregarPersona(persona);
            if(identificarTipoSolicitud(solicitudRegistro) == "E" ){
                /**Solicitud de tipo Externo */
                agregarContacto(contacto);

            }
            else{
                /**Solicitud de tipo Interno */

            }
        }


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


        Contacto auxContacto = new Contacto(auxSolicitud.getDni(), solicitudExterno.getRuc());

        responderSolicitud(solicitudRespuesta);
        if(solicitudRespuesta.getEstado().equals("Rechazado")){
            eliminarContacto(auxContacto);
        }
    }


    private String identificarTipoSolicitud (SolicitudRegistro solicitudRegistro){
        String aux="E";
        if(solicitudRegistro.getRuc().isBlank()){
            aux="I";
        }
        return aux;
    }
    private void agregarSolicitud (Solicitud solicitud){
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String SQL=" INSERT INTO SOLICITUD(DNI, FECHA_SOLICITUD, ESTADO, COD_TIPO) " +
                    " VALUES (?, (SELECT CURRENT_DATE), 'Pendiente', ?) ";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1,solicitud.getDni());
            ps.setString(2,solicitud.getCodigoTipo());
            ps.executeUpdate();
            ps.close();
            con.commit();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    /**Registro*/
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

    private Boolean verificarDNIenPersona (Buscar buscar){
        /**Aquí se comprueba que el DNI exista o no*/
        return true;
    }
    private Boolean verificarDNIadmitido (Buscar buscar){
        /**Aquí se comprueba que el DNI exista o no*/
        return true;
    }

    /**Personas*/
    private void agregarPersona (Persona persona){
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String SQL=" INSERT INTO PERSONA(DNI, PRIMER_NOMBRE, APELLIDO_M, APELLIDO_P, CONTRASENIA, FIRMA, COD_DIRECCION) " +
                    " VALUES ( ?, ?, ?, ?, ?, ?, ? ) ";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1,persona.getDni());
            ps.setString(2,persona.getPrimerNombre());
            ps.setString(3,persona.getApellidoMaterno());
            ps.setString(4,persona.getApellidoPaterno());
            ps.setString(5,persona.getContrasenia());
            ps.setString(6,persona.getFirma());
            ps.setInt(7, persona.getCodigoDireccion());
            ps.executeUpdate();
            ps.close();
            con.commit();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private void modificarPersona (Persona persona){
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String SQL=" UPDATE PERSONA SET " +
                    " PRIMER_NOMBRE = ?, APELLIDO_M = ?, APELLIDO_P = ?, FIRMA = ?, CONTRASENIA = ? " +
                    " WHERE DNI = ? ";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1,persona.getPrimerNombre());
            ps.setString(2,persona.getApellidoMaterno());
            ps.setString(3,persona.getApellidoPaterno());
            ps.setString(4,persona.getFirma());
            ps.setString(5,persona.getContrasenia());
            ps.setString(6,persona.getDni());
            ps.executeUpdate();
            ps.close();
            con.commit();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /**Direcciones*/
    private Integer obtenerCodigoUltimaDireccion(){
        /**Aquí se obtiene el codigo de la direccion*/
        Integer elemento=0;
        try{
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String sql=" SELECT COD_DIRECCION FROM DIRECCION ORDER BY COD_DIRECCION DESC LIMIT 1";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                elemento=rs.getInt("COD_DIRECCION");
            }
            rs.close();
            ps.close();
            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return elemento;

    }
    private void agregarDireccion (Direccion direccion){
        /**Aquí se agrega la direccion en caso la direccion no exista*/
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String SQL=" INSERT INTO DIRECCION(COD_POSTAL, NOMBRE, UNIDAD, DISTRITO, PROVINCIA, PAIS) " +
                    " VALUES ( ?, ?, ?, ?, ?, ? ) ";

            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1,direccion.getCodigoPostal());
            ps.setString(2,direccion.getDireccion());
            ps.setString(3,direccion.getUnidad());
            ps.setString(4,direccion.getDistrito());
            ps.setString(5,direccion.getProvincia());
            ps.setString(6,direccion.getPais());
            ps.executeUpdate();
            ps.close();
            con.commit();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    private void modificarDireccion (Direccion direccion){
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String SQL=" UPDATE DIRECCION SET " +
                    "COD_POSTAL=  ?, NOMBRE= ?, UNIDAD= ?, DISTRITO= ?, PROVINCIA= ?, PAIS= ? " +
                    "WHERE COD_DIRECCION = ? ";

            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1,direccion.getCodigoPostal());
            ps.setString(2,direccion.getDireccion());
            ps.setString(3,direccion.getUnidad());
            ps.setString(4,direccion.getDistrito());
            ps.setString(5,direccion.getProvincia());
            ps.setString(6,direccion.getPais());
            ps.setInt(7, direccion.getCodigoDireccion());
            ps.executeUpdate();
            ps.close();
            con.commit();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private Direccion traerDireccionPorCodigoDireccion (Buscar buscar){
        Direccion elemento = new Direccion();
        try{
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String sql=" SELECT COD_DIRECCION, COD_POSTAL, NOMBRE, UNIDAD, DISTRITO, PROVINCIA, PAIS FROM DIRECCION WHERE COD_DIRECCION = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, buscar.getContenidoNumerico());;
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                elemento.setCodigoDireccion(rs.getInt("COD_DIRECCION"));
                elemento.setCodigoPostal(rs.getString("COD_POSTAL"));
                elemento.setDireccion(rs.getString("NOMBRE"));
                elemento.setUnidad(rs.getString("UNIDAD"));
                elemento.setDistrito(rs.getString("DISTRITO"));
                elemento.setProvincia(rs.getString("PROVINCIA"));
                elemento.setPais(rs.getString("PAIS"));
            }
            rs.close();
            ps.close();
            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return elemento;
    }
    /** Telefonos */
    private void agregarTelefono (Telefono telefono){
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String SQL= " INSERT INTO TELEFONO(DNI, PREFIJO, NUMERO) " +
                    " VALUES ( ?, ?, ? ) ";

            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1,telefono.getDni());
            ps.setString(2,telefono.getPrefijo());
            ps.setString(3,telefono.getNumero());
            ps.executeUpdate();
            ps.close();
            con.commit();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private void modificarTelefono (Telefono telefono){
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String SQL= " UPDATE TELEFONO SET " +
                    " NUMERO= ?, " +
                    " PREFIJO= ? " +
                    " WHERE DNI= ? ";

            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1,telefono.getNumero());
            ps.setString(2,telefono.getPrefijo());
            ps.setString(3,telefono.getDni());
            ps.executeUpdate();
            ps.close();
            con.commit();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private void eliminarTelefono (Telefono telefono){
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String SQL= " DELETE FROM TELEFONO " +
                    " WHERE COD_TELEFONO = ? ";

            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1,telefono.getCodigoTelefono());
            ps.executeUpdate();
            ps.close();
            con.commit();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    private List<Telefono> traerTelefonos (Buscar buscar){
        List<Telefono> lista = new ArrayList<>();
        try{
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String sql=" SELECT NUMERO, PREFIJO, COD_TELEFONO, DNI FROM TELEFONO WHERE DNI = ? ORDER BY PREFIJO";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, buscar.getContenido());;
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Telefono elemento = new Telefono();
                elemento.setNumero(rs.getString("NUMERO"));
                elemento.setPrefijo(rs.getString("PREFIJO"));
                elemento.setCodigoTelefono(rs.getInt("COD_TELEFONO"));
                elemento.setDni(rs.getString("DNI"));
                lista.add(elemento);
            }
            rs.close();
            ps.close();
            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lista;



    }
    /** Correos */
    private void agregarCorreo (Correo correo){
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String SQL= " INSERT INTO CORREO(CORREO, DNI) " +
                    " VALUES ( ?, ? ) ";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1,correo.getCorreo());
            ps.setString(2,correo.getDni());
            ps.executeUpdate();
            ps.close();
            con.commit();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    private void modificarCorreo (Correo correo){
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String SQL= " UPDATE CORREO SET " +
                    " CORREO = ? " +
                    " WHERE DNI= ? ";

            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1,correo.getCorreo());
            ps.setString(2,correo.getDni());
            ps.executeUpdate();
            ps.close();
            con.commit();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private void eliminarCorreo (Correo correo){
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String SQL= " DELETE FROM CORREO " +
                    " WHERE COD_CORREO = ? ";

            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setInt(1,correo.getCodigoCorreo());
            ps.executeUpdate();
            ps.close();
            con.commit();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    private List<Correo> traerCorreos (Buscar buscar){
        List<Correo> lista = new ArrayList<>();
        try{
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String sql=" SELECT CORREO, COD_CORREO, DNI FROM TELEFONO WHERE DNI = ? ORDER BY CORREO ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, buscar.getContenido());;
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Correo elemento = new Correo();
                elemento.setCorreo(rs.getString("CORREO"));
                elemento.setCodigoCorreo(rs.getInt("COD_CORREO"));
                elemento.setDni(rs.getString("DNI"));
                lista.add(elemento);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lista;

    }
    /** Perfiles*/
    private void agregarPerfil (TrabajadorPerfilNombre trabajadorPerfilNombre){
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String SQL= " INSERT INTO TRABAJADOR_PERFIL( COD_PERFIL, DNI ) " +
                    " VALUES ( ?, ? ) ";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1,trabajadorPerfilNombre.getCodigoPerfil());
            ps.setString(2,trabajadorPerfilNombre.getDni());
            ps.executeUpdate();
            ps.close();
            con.commit();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
    private void eliminarPerfil (TrabajadorPerfilNombre trabajadorPerfilNombre){
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String SQL= " DELETE FROM TRABAJADOR_PERFIL " +
                    " WHERE COD_PERFIL = ? AND DNI = ? ";
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1,trabajadorPerfilNombre.getCodigoPerfil());
            ps.setString(2,trabajadorPerfilNombre.getDni());
            ps.executeUpdate();
            ps.close();
            con.commit();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    private List<TrabajadorPerfilNombre> traerTrabajadorPerfiles (Buscar buscar){
        List<TrabajadorPerfilNombre> lista = new ArrayList<>();
        try{
            Connection con = jdbcTemplate.getDataSource().getConnection();
            String sql=" SELECT TP.COD_PERFIL, TP.DNI, P.NOMBRE FROM TRABAJADOR_PERFIL TP, PERFIL P WHERE TP.COD_PERFIL = P.COD_PERFIL AND DNI = ? ORDER BY CORREO ";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, buscar.getContenido());;
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                TrabajadorPerfilNombre elemento = new TrabajadorPerfilNombre();
                elemento.setCodigoPerfil(rs.getString("COD_PERFIL"));
                elemento.setDni(rs.getString("DNI"));
                elemento.setNombre(rs.getString("NOMBRE"));
                lista.add(elemento);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return lista;

    }
}
