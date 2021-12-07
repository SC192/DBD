package com.edu.fiis.assetecback.dao;

import com.edu.fiis.assetecback.dto.*;
import com.edu.fiis.assetecback.dto.responses.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AssetecDaoImpl implements AssetecDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Proyecto> proyectoGerentes(String dni){
        List<Proyecto> proyectos = new ArrayList<>();
        String sql = "SELECT\n" +
                "NOMBRE,\n" +
                "COD_PROYECTO,\n" +
                "ESTADO,\n" +
                "DESCRIPCION,\n" +
                "FECHA_FIN_REAL,\n" +
                "FECHA_FIN_EST,\n" +
                "FECHA_INICIO_REAL,\n" +
                "FECHA_INICIO_EST \n" +
                "FROM PROYECTO_TRABAJADOR_V\n" +
                "WHERE (COD_PERFIL = 'GP' OR COD_PERFIL = 'JP')\n" +
                "AND DNI_TRAB = ?";
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, dni);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Proyecto proyecto = new Proyecto();
                proyecto.setCodigoProyecto(rs.getString("COD_PROYECTO"));
                proyecto.setEstado(rs.getString("ESTADO"));
                proyecto.setNombre(rs.getString("NOMBRE"));
                proyecto.setDescripcion(rs.getString("DESCRIPCION"));
                proyecto.setFechaFinReal(rs.getString("FECHA_FIN_REAL"));
                proyecto.setFechaFinEstimada(rs.getString("FECHA_FIN_EST"));
                proyecto.setFechaInicioReal(rs.getString("FECHA_INICIO_REAL"));
                proyecto.setFechaInicioEstimada(rs.getString("FECHA_INICIO_EST"));
                proyectos.add(proyecto);
            }
            rs.close();
            ps.close();
            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return proyectos;
    }

    public List<Proyecto> proyectoOtrosUsuarios(String dni){
        List<Proyecto> proyectos = new ArrayList<>();
        String sql = "SELECT\n" +
                "NOMBRE,\n" +
                "COD_PROYECTO,\n" +
                "ESTADO,\n" +
                "DESCRIPCION,\n" +
                "FECHA_FIN_REAL,\n" +
                "FECHA_FIN_EST,\n" +
                "FECHA_INICIO_REAL,\n" +
                "FECHA_INICIO_EST \n" +
                "FROM PROYECTO_TRABAJADOR_V\n" +
                "WHERE NOT (COD_PERFIL = 'GP' OR COD_PERFIL = 'JP')\n" +
                "AND NOT ESTADO = 'DESAPROBADO'\n" +
                "AND DNI_TRAB = ?";
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, dni);

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Proyecto proyecto = new Proyecto();
                proyecto.setCodigoProyecto(rs.getString("COD_PROYECTO"));
                proyecto.setEstado(rs.getString("ESTADO"));
                proyecto.setNombre(rs.getString("NOMBRE"));
                proyecto.setDescripcion(rs.getString("DESCRIPCION"));
                proyecto.setFechaFinReal(rs.getString("FECHA_FIN_REAL"));
                proyecto.setFechaFinEstimada(rs.getString("FECHA_FIN_EST"));
                proyecto.setFechaInicioReal(rs.getString("FECHA_INICIO_REAL"));
                proyecto.setFechaInicioEstimada(rs.getString("FECHA_INICIO_EST"));
                proyectos.add(proyecto);
            }
            rs.close();
            ps.close();
            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return proyectos;
    }

    public List<Actividad> obtenerActividadesProyecto(Proyecto proyecto) {
        List<Actividad> actividades = new ArrayList<>();
        String sql = "SELECT\n" +
                "A.COD_ACTIVIDAD,\n" +
                "A.COD_ACTIVIDAD_PADRE,\n" +
                "A.FECHA_FIN_REAL,\n" +
                "A.FECHA_FIN_EST,\n" +
                "A.FECHA_INICIO_REAL,\n" +
                "A.FECHA_INICIO_EST,\n" +
                "A.NOMBRE,\n" +
                "A.POSICION\n" +
                "FROM PROYECTO P\n" +
                "INNER JOIN ACTIVIDAD A\n" +
                "ON P.COD_PROYECTO = A.COD_PROYECTO\n" +
                "WHERE P.COD_PROYECTO = ?";

        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, proyecto.getCodigoProyecto());

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Actividad actividad = new Actividad();
                actividad.setCodigoActividad(rs.getString("COD_ACTIVIDAD"));
                actividad.setCodigoActividadPadre(rs.getString("COD_ACTIVIDAD_PADRE"));
                actividad.setFechaFinReal(rs.getString("FECHA_FIN_REAL"));
                actividad.setFechaFinEstimada(rs.getString("FECHA_FIN_EST"));
                actividad.setFechaInicioReal(rs.getString("FECHA_INICIO_REAL"));
                actividad.setFechaInicioEstimada(rs.getString("FECHA_INICIO_EST"));
                actividad.setNombre(rs.getString("NOMBRE"));
                actividad.setPosicion(rs.getInt("POSICION"));
                actividades.add(actividad);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return actividades;
    }

    public List<Alcance> obtenerAlcancesProyecto(Proyecto proyecto) {
        List<Alcance> alcances = new ArrayList<>();
        String sql = "SELECT ALCANCE FROM ALCANCE\n" +
                "WHERE COD_PROYECTO = ?";

        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, proyecto.getCodigoProyecto());

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Alcance alcance = new Alcance();
                alcance.setAlcance(rs.getString("ALCANCE"));
                alcances.add(alcance);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return alcances;
    }

    public List<Objetivo> obtenerObjetivosProyecto(Proyecto proyecto) {
        List<Objetivo> objetivos = new ArrayList<>();
        String sql = "SELECT POSICION, DESCRIPCION FROM OBJETIVO WHERE COD_PROYECTO = ?";

        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, proyecto.getCodigoProyecto());
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Objetivo o = new Objetivo();
                o.setDescripcion(rs.getString("DESCRIPCION"));
                o.setPosicion(rs.getInt("POSICION"));
                objetivos.add(o);
            }
            rs.close();
            ps.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return objetivos;
    }

    public List<Rol> obtenerListaRoles(Proyecto proyecto) {
        List<Rol> roles = new ArrayList<>();
        String sql = "SELECT\n" +
                "PTV.NOMBRE_PERFIL,\n" +
                "COUNT(DISTINCT PTV.DNI_TRAB) AS CANTIDAD,\n" +
                "SUM(TPA.CANTI_HORAS) AS TOTAL_HORAS,\n" +
                "MAX(CONVERTIR_MONTO(SUELDO_PERFIL, NOMBRE_MONEDA)) AS COSTO_HORA\n" +
                "FROM PROYECTO_TRABAJADOR_V PTV\n" +
                "INNER JOIN TRABAJADOR_PERFIL_ACTIVIDAD TPA\n" +
                "ON PTV.DNI_TRAB = TPA.DNI AND PTV.COD_PERFIL = TPA.COD_PERFIL\n" +
                "WHERE COD_PROYECTO = ? \n" +
                "GROUP BY PTV.NOMBRE_PERFIL";

        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, proyecto.getCodigoProyecto());

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Rol rol = new Rol();
                rol.setNombrePerfil(rs.getString("NOMBRE_PERFIL"));
                rol.setCantidad(rs.getInt("CANTIDAD"));
                rol.setTotalHoras(rs.getDouble("TOTAL_HORAS"));
                rol.setCostoHora(rs.getDouble("COSTO_HORA"));
                roles.add(rol);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roles;
    }

    public List<ObjetivoActividad> obtenerObjetivosActividad(Actividad actividad) {
        List<ObjetivoActividad> oa = new ArrayList<>();
        String sql = "SELECT OBJETIVO FROM OBJETIVO_A WHERE COD_ACTIVIDAD = ?";

        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1,actividad.getCodigoActividad());

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                ObjetivoActividad o = new ObjetivoActividad();
                o.setObjetivo(rs.getString("OBJETIVO"));
                oa.add(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oa;
    }

    public void completarActividad(Actividad actividad) {
        String sql = "UPDATE ACTIVIDAD\n" +
                "SET FECHA_FIN_REAL = CURRENT_DATE\n" +
                "WHERE COD_ACTIVIDAD = ?\n";
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, actividad.getCodigoActividad());

            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
