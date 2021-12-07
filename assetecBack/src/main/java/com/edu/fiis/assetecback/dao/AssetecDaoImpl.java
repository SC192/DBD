package com.edu.fiis.assetecback.dao;

import com.edu.fiis.assetecback.dto.*;
import com.edu.fiis.assetecback.dto.request.RegistroAsistencia;
import com.edu.fiis.assetecback.dto.request.TrabajadorActividad;
import com.edu.fiis.assetecback.dto.responses.ResumenTrabajador;
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
                "COD_ACTIVIDAD,\n" +
                "COD_ACTIVIDAD_PADRE,\n" +
                "FECHA_FIN_REAL,\n" +
                "FECHA_FIN_EST,\n" +
                "FECHA_INICIO_REAL,\n" +
                "FECHA_INICIO_EST,\n" +
                "NOMBRE,\n" +
                "POSICION,\n" +
                "DESCRIPCION,\n" +
                "COD_GASTO\n" +
                "FROM ACTIVIDAD \n" +
                "WHERE COD_PROYECTO = ?";

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
                actividad.setDescripcion(rs.getString("DESCRIPCION"));
                actividad.setCodigoGasto(rs.getString("COD_GASTO"));
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

    public List<Rol> obtenerListaRolesProyecto(Proyecto proyecto) {
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

    public List<Rol> obtenerListaRolesActividad(Actividad actividad) {
        List<Rol> roles = new ArrayList<>();
        String sql = "SELECT\n" +
                "PER.NOMBRE AS ROL,\n" +
                "COUNT(DISTINCT TPA.DNI) AS CANTIDAD,\n" +
                "SUM(TPA.CANTI_HORAS) AS HORAS_R,\n" +
                "MAX(convertir_monto(PER.SUELDO, M.NOMBRE)) AS COSTO_HORA\n" +
                "FROM TRABAJADOR_PERFIL_ACTIVIDAD TPA\n" +
                "INNER JOIN TRABAJADOR_PERFIL TP\n" +
                "ON TPA.DNI = TP.DNI AND TPA.COD_PERFIL = TP.COD_PERFIL\n" +
                "INNER JOIN PERFIL PER\n" +
                "ON PER.COD_PERFIL = TP.COD_PERFIL\n" +
                "INNER JOIN MONEDA M\n" +
                "ON PER.COD_MONEDA = M.COD_MONEDA\n" +
                "WHERE COD_ACTIVIDAD = <1>\n" +
                "GROUP BY PER.NOMBRE";
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,actividad.getCodigoActividad());

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Rol rol = new Rol();
                rol.setNombrePerfil(rs.getString("ROL"));
                rol.setCantidad(rs.getInt("CANTIDAD"));
                rol.setTotalHoras(rs.getDouble("HORAS_R"));
                rol.setCostoHora(rs.getDouble("COSTO_HORA"));
                roles.add(rol);
            }

            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roles;
    }

    public RegistroAsistencia obtenerTrabajadorActividad(TrabajadorActividad ta) {
        RegistroAsistencia ra = new RegistroAsistencia();
        String sql = "SELECT\n" +
                "DISTINCT NOMBRE_PERFIL AS ROL,\n" +
                "convertir_monto(SUELDO_PERFIL,NOMBRE_MONEDA) AS COSTO_HORA,\n" +
                "DNI_TRAB AS DNI,\n" +
                "PRIMER_NOMBRE_TRAB AS PRIMER_NOMBRE,\n" +
                "APELLIDO_M_TRAB AS APELLIDO_M,\n" +
                "APELLIDO_P_TRAB AS APELLIDO_P\n" +
                "FROM PROYECTO_TRABAJADOR_V TPV\n" +
                "INNER JOIN TRABAJADOR_PERFIL_ACTIVIDAD TPA\n" +
                "ON TPV.DNI_TRAB = TPA.DNI\n" +
                "WHERE TPA.DNI = ?\n" +
                "AND TPA.COD_ACTIVIDAD = ?\n" +
                "AND TPV.COD_PROYECTO = ?";
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, ta.getDni());
            ps.setString(2, ta.getCodigoActividad());
            ps.setString(3, ta.getCodigoProyecto());

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                ra.setNombreRol(rs.getString("ROL"));
                ra.setCostoHora(rs.getDouble("COSTO_HORA"));
                ra.setDni(rs.getString("DNI"));
                ra.setNombre(rs.getString("PRIMER_NOMBRE"));
                ra.setApellidoM(rs.getString("APELLIDO_M"));
                ra.setApellidoP(rs.getString("APELLIDO_P"));
            }

            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ra;
    }

    public void registrarAsistenciaTrabajador(RegistroAsistencia ra) {
        String sql = "INSERT INTO\n" +
                "TRABAJADOR_PERFIL_ACTIVIDAD(CODIGO,FECHA,CANT_HORAS,COD_ACTIVIDAD,DNI,\n" +
                "COD_PERFIL)\n" +
                "VALUES (gen_cod('TRABAJADOR_PERFIL_ACTIVIDAD'),TO_DATE(?,‘DD/MM/YYYY’), \n" +
                "?, ?, ?,(SELECT COD_PERFIL FROM PERFIL WHERE NOMBRE = ?))";

        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, ra.getFecha());
            ps.setDouble(2, ra.getHorasTrabajadas());
            ps.setString(3, ra.getCodigoActividad());
            ps.setString(4, ra.getDni());
            ps.setString(5, ra.getNombreRol());

            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ResumenTrabajador> obtenerResumenTrabajadorActividad(Actividad actividad) {
        List<ResumenTrabajador> resumenTrabajador = new ArrayList<>();
        String sql = "SELECT\n" +
                "PER.NOMBRE AS ROL,\n" +
                "MAX(convertir_monto(PER.SUELDO, M.NOMBRE)) AS COSTO_HORA,\n" +
                "PRS.DNI,\n" +
                "MAX(PRS.PRIMER_NOMBRE) AS NOMBRE,\n" +
                "MAX(PRS.APELLIDO_P) AS APELLIDO_P,\n" +
                "SUM(TPA.CANT_HORAS) AS HORAS_T\n" +
                "FROM TRABAJADOR_PERFIL_ACTIVIDAD TPA\n" +
                "INNER JOIN TRABAJADOR_PERFIL TP\n" +
                "ON TPA.DNI = TP.DNI AND TPA.COD_PERFIL = TP.COD_PERFIL\n" +
                "INNER JOIN PERSONA PRS\n" +
                "ON TP.DNI = PRS.DNI\n" +
                "INNER JOIN PERFIL PER\n" +
                "ON PER.COD_PERFIL = TP.COD_PERFIL\n" +
                "INNER JOIN MONEDA M\n" +
                "ON PER.COD_MONEDA = M.COD_MONEDA\n" +
                "WHERE COD_ACTIVIDAD = ?\n" +
                "GROUP BY PER.NOMBRE, PRS.DNI";

        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, actividad.getCodigoActividad());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ResumenTrabajador rt = new ResumenTrabajador();
                rt.setNombreRol(rs.getString("ROL"));
                rt.setCostoHora(rs.getDouble("COSTO_HORA"));
                rt.setDni(rs.getString("DNI"));
                rt.setNombre(rs.getString("NOMBRE"));
                rt.setApellidoP(rs.getString("APELLIDO_P"));
                rt.setHorasTotales(rs.getDouble("HORAS_T"));
                resumenTrabajador.add(rt);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resumenTrabajador;
    }
}
