package com.edu.fiis.assetecback.dao;

import com.edu.fiis.assetecback.dto.*;
import com.edu.fiis.assetecback.dto.request.*;
import com.edu.fiis.assetecback.dto.responses.ReporteResponse;
import com.edu.fiis.assetecback.dto.responses.ResumenTrabajador;
import com.edu.fiis.assetecback.dto.responses.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class AssetecDaoImpl implements AssetecDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Boolean esCliente(Persona persona) {
        boolean ans = true;
        String sql = "select exists( " +
                "select * from proyecto pr " +
                "inner join proyecto_cliente pc " +
                "  on pr.cod_proyecto = pc.cod_proyecto " +
                "inner join contacto c " +
                "  on c.dni = pc.dni " +
                "inner join persona pe " +
                "  on c.dni = pe.dni " +
                "where c.dni = ?)";
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, persona.getDni());

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                ans = rs.getBoolean("exists");
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public List<Proyecto> treaerProyectosUsuario(Persona persona){
        List<Proyecto> proyectos = new ArrayList<>();
        String adicional;
        if(esCliente(persona)) {
            adicional = " from proyecto p " +
                    "inner join proyecto_cliente pc " +
                    "  on p.cod_proyecto = pc.cod_proyecto " +
                    "inner join contacto c " +
                    "  on c.dni = pc.dni " +
                    "inner join persona pe " +
                    "  on c.dni = pe.dni " +
                    "where c.dni = ?";
        } else {
            adicional = " FROM PROYECTO_TRABAJADOR_V p " +
                    "WHERE DNI_TRAB = ?";
        }
        String sql = "SELECT " +
                " NOMBRE, " +
                " P.COD_PROYECTO, " +
                " ESTADO, " +
                " DESCRIPCION, " +
                " FECHA_FIN_REAL, " +
                " FECHA_FIN_EST, " +
                " FECHA_INICIO_REAL, " +
                " FECHA_INICIO_EST " + adicional;
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, persona.getDni());

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
        String sql = "SELECT ALCANCE FROM ALCANCE " +
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
            con.commit();
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

    public Asistencia obtenerTrabajadorActividad(TrabajadorActividad ta) {
        Asistencia ra = new Asistencia();
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

    public void registrarAsistenciaTrabajador(Asistencia ra) {
        String sql = "INSERT INTO\n" +
                "TRABAJADOR_PERFIL_ACTIVIDAD(CODIGO, FECHA,CANT_HORAS,COD_ACTIVIDAD,DNI,COD_PERFIL)\n" +
                "VALUES (gen_cod('TRABAJADOR_PERFIL_ACTIVIDAD'),?, ?, ?, ?,(SELECT COD_PERFIL FROM PERFIL WHERE NOMBRE = ?))";

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

    public List<Asistencia> traerDatosTrabajadorActividad(Asistencia asistencia) {
        List<Asistencia> asistencias = new ArrayList<>();
        String sql = "SELECT\n" +
                "CODIGO,\n" +
                "FECHA,\n" +
                "CANTI_HORAS,\n" +
                "FROM TRABAJADOR_PERFIL_ACTIVIDAD\n" +
                "WHERE COD_ACTIVIDAD = ? \n" +
                "AND DNI = ? \n" +
                "AND COD_PERFIL = (SELECT COD_PERFIL FROM PERFIL WHERE NOMBRE = ?)";
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, asistencia.getCodigoActividad());
            ps.setString(2, asistencia.getDni());
            ps.setString(3, asistencia.getNombreRol());

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Asistencia a = new Asistencia();
                a.setNombreRol(asistencia.getNombreRol());
                a.setCostoHora(asistencia.getCostoHora());
                a.setDni(asistencia.getDni());
                a.setNombre(asistencia.getNombre());
                a.setApellidoP(asistencia.getApellidoP());
                a.setApellidoM(asistencia.getApellidoM());
                a.setCodigo(rs.getInt("CODIGO"));
                a.setFecha(rs.getString("FECHA"));
                a.setHorasTrabajadas(rs.getDouble("CANTI_HORAS"));
                asistencias.add(a);
            }

            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return asistencias;
    }

    public void eliminarAsistenciaTrabajador(Asistencia asistencia){
        String sql = "DELETE FROM TRABAJADOR_PERFIL_ACTIVIDAD\n" +
                "WHERE CODIGO = ?\n";
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, asistencia.getCodigo());

            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void registrarPago(RegistroPago registroPago) {
        String sql = "CALL registrar_remu(?,?,?)";
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, registroPago.getCodigoActividad());
            ps.setDouble(2, registroPago.getMaximaRemumeracion());
            ps.setString(3, registroPago.getNombreMoneda());

            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void registrarComprobantePago(RegistroComprobante registroComprobante) {
        String sql = "INSERT INTO COMPROBANTE_PAGO(NUMERO, IMPORTE,PROVEEDOR, FECHA,\n" +
                "DESCRIPCION, COD_MONEDA, COD_TIPO_COMP, COD_TIPO_GASTO, COD_ACTIVIDAD)\n" +
                "VALUES\n" +
                "(?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "?,\n" +
                "(SELECT COD_MONEDA FROM MONEDA WHERE NOMBRE = UPPER(?)),\n" +
                "(SELECT COD_TIPO FROM TIPO_COMPROBANTE WHERE TIPO = UPPER(?)),\n" +
                "(SELECT COD_TIPO FROM TIPO_GASTO WHERE TIPO = UPPER(?)),\n" +
                "?)";
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, registroComprobante.getNumero());
            ps.setDouble(2, registroComprobante.getImporte());
            ps.setString(3, registroComprobante.getProveedor());
            ps.setString(4, registroComprobante.getFecha());
            ps.setString(5, registroComprobante.getDescripcion());
            ps.setString(6, registroComprobante.getNombreMoneda());
            ps.setString(7, registroComprobante.getNombreTipoComprobante());
            ps.setString(8, registroComprobante.getNombreTipoGasto());
            ps.setString(9, registroComprobante.getCodigoActividad());

            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void generarCierre(Proyecto proyecto) {
        String sql = "UPDATE PROYECTO\n" +
                "SET ESTADO = 'EN ESPERA'\n" +
                "WHERE COD_PROYECTO = <1>";
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, proyecto.getCodigoProyecto());

            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crearActa(RegistroActa registroActa) {
        String sql = "CALL crear_acta(?,?)";
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            CallableStatement proc = con.prepareCall(sql);

            Array sqlArray = con.createArrayOf("varchar", registroActa.getAcuerdos().toArray(new String[0]));
            proc.setArray(1, sqlArray);
            proc.setString(2, registroActa.getCodigoProyecto());

            proc.execute();
            con.commit();
            proc.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void aceptarActa(RespuestaCliente respuestaCliente) {
        String sql = "CALL aceptar_acta(?,?);";
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            CallableStatement proc = con.prepareCall(sql);

            proc.setString(1, respuestaCliente.getDni());
            proc.setString(2, respuestaCliente.getCodigoProyecto());

            proc.executeUpdate();
            con.commit();
            proc.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rechazarActa(RespuestaCliente respuestaCliente) {
        String sql = "CALL rechazar_acta(?,?);";
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            CallableStatement proc = con.prepareCall(sql);

            proc.setString(1, respuestaCliente.getDni());
            proc.setString(2, respuestaCliente.getCodigoProyecto());

            proc.executeUpdate();
            con.commit();
            proc.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ReporteResponse> traerReportesProyecto(Proyecto proyecto) {
        List<ReporteResponse> reporteResponses = new ArrayList<>();
        String sql = "SELECT\n" +
                "R.FECHA AS FECHA_REPORTE,\n" +
                "R.DESCRIPCION AS DESCRIPCION_REPORTE,\n" +
                "R.NRO_REPORTE,\n" +
                "TR.TIPO AS TIPO_REPORTE,\n" +
                "FROM REPORTE R\n" +
                "INNER JOIN TIPO_REPORTE TR\n" +
                " ON TR.COD_TIPO = R.COD_TIPO\n" +
                "WHERE COD_PROYECTO = ?";
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, proyecto.getCodigoProyecto());
            
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                ReporteResponse rr = new ReporteResponse();
                rr.setNumeroReporte(rs.getInt("NRO_REPORTE"));
                rr.setTipoReporte(rs.getString("TIPO_REPORTE"));
                rr.setDescripcion(rs.getString("DESCRIPCION_REPORTE"));
                rr.setFecha(rs.getString("FECHA_REPORTE"));
                reporteResponses.add(rr);
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reporteResponses;
    }

    public List<String> reporteGastosActividad(Proyecto proyecto) {
        List<String> oraciones = new ArrayList<>();
        String sql = "SELECT\n" +
                "'En la actividad ' || (SELECT NOMBRE FROM ACTIVIDAD WHERE COD_ACTIVIDAD = PA.COD_ACTIVIDAD_PADRE) ||\n" +
                "' se gasto ' || SUM(CONVERTIR_MONTO(CP.IMPORTE,M1.NOMBRE)) || ' soles.'||\n" +
                "'Además la empresa cubre con los gastos hasta por ' ||\n" +
                "MAX(CONVERTIR_MONTO(G.MAX_REMU,M2.NOMBRE)) || ' soles.' AS oracion\n" +
                "FROM PROYECTO_ACTIVIDADES PA\n" +
                "INNER JOIN COMPROBANTE_PAGO CP\n" +
                "ON PA.COD_ACTIVIDAD_PADRE = CP.COD_ACTIVIDAD OR PA.CODIGO_ACT_HIJA = CP.COD_ACTIVIDAD\n" +
                "INNER JOIN GASTO G\n" +
                "ON PA.COD_GASTO = G.COD_GASTO\n" +
                "INNER JOIN MONEDA M1\n" +
                "ON CP.COD_MONEDA = M1.COD_MONEDA\n" +
                "INNER JOIN MONEDA M2\n" +
                "ON G.COD_MONEDA = M2.COD_MONEDA\n" +
                "WHERE PA.CODIGO_PROYECTO = ?\n" +
                "GROUP BY PA.COD_ACTIVIDAD_PADRE";
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, proyecto.getCodigoProyecto());

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                oraciones.add(rs.getString("oracion"));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oraciones;
    }

    public String reporteFechasProyecto(Proyecto proyecto) {
        String texto = "";
        String sql = "SELECT\n" +
                "'El proyecto ' || nombre ||\n" +
                "CASE\n" +
                "  WHEN FECHA_INICIO_REAL - FECHA_INICIO_EST = 1\n" +
                "    THEN ' tardo 1 dia en iniciar.'\n" +
                "  WHEN FECHA_INICIO_REAL - FECHA_INICIO_EST > 0\n" +
                "    THEN ' tardo ' || FECHA_INICIO_REAL - FECHA_INICIO_EST || ' dias en iniciar.'\n" +
                "  WHEN FECHA_INICIO_REAL - FECHA_INICIO_EST =-1\n" +
                "    THEN ' comenzo 1 dia antes.'\n" +
                "  WHEN FECHA_INICIO_REAL - FECHA_INICIO_EST < 0\n" +
                "    THEN ' comenzo ' || FECHA_INICIO_EST - FECHA_INICIO_REAL || ' dias antes.'\n" +
                "  ELSE ' inicio sin retraso.'\n" +
                "END\n" +
                "|| ' Asimismo, el proyecto ' ||\n" +
                "CASE\n" +
                "  WHEN FECHA_FIN_REAL - FECHA_FIN_EST = 1\n" +
                "    THEN ' tardo 1 dia en finalizar.'\n" +
                "  WHEN FECHA_FIN_REAL - FECHA_FIN_EST > 0119\n" +
                "    THEN ' tardo ' || FECHA_FIN_REAL - FECHA_FIN_EST || ' dias en finalizar.'\n" +
                "  WHEN FECHA_FIN_REAL - FECHA_FIN_EST =-1\n" +
                "    THEN ' finalizo 1 dia antes.'\n" +
                "  WHEN FECHA_FIN_REAL - FECHA_FIN_EST < 0\n" +
                "    THEN ' finalizo ' || FECHA_FIN_EST - FECHA_FIN_REAL || ' dias antes.'\n" +
                "  ELSE ' finalizo sin retraso.'\n" +
                "END AS TEXTO\n" +
                "FROM PROYECTO\n" +
                "WHERE COD_PROYECTO = ?";
        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, proyecto.getCodigoProyecto());

            ResultSet rs = ps.executeQuery();
            texto = rs.getString("TEXTO");
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return texto;
    }

    public List<String> reporteFechasActividadesProyecto(Proyecto proyecto) {
        List<String> oraciones = new ArrayList<>();
        String sql = "SELECT\n" +
                "'La actividad ' || (SELECT NOMBRE FROM ACTIVIDAD WHERE COD_ACTIVIDAD = PA.COD_ACTIVIDAD_PADRE) ||\n" +
                "CASE\n" +
                "  WHEN MIN(pa.fecha_inicio_real_act_padre) - MIN(pa.fecha_inicio_est_act_padre) = 1\n" +
                "    THEN ' tardo 1 dia en iniciar.'\n" +
                "  WHEN MIN(pa.fecha_inicio_real_act_padre) - MIN(pa.fecha_inicio_est_act_padre) > 0\n" +
                "    THEN ' tardo ' || MIN(pa.fecha_inicio_real_act_padre) - MIN(pa.fecha_inicio_est_act_padre) || ' dias en iniciar.'\n" +
                "  WHEN MIN(pa.fecha_inicio_real_act_padre) - MIN(pa.fecha_inicio_est_act_padre) = -1\n" +
                "    THEN ' comenzo un dia antes.'\n" +
                "  WHEN MIN(pa.fecha_inicio_real_act_padre) - MIN(pa.fecha_inicio_est_act_padre) < 0\n" +
                "    THEN ' comenzo ' || MIN(pa.fecha_inicio_real_act_padre) - MIN(pa.fecha_inicio_est_act_padre) || ' dias antes.'\n" +
                "  ELSE ' inicio sin retraso.'\n" +
                "END\n" +
                "|| ' Asimismo, la actividad ' ||\n" +
                "CASE\n" +
                "  WHEN MAX(pa.fecha_fin_real_act_padre) - MAX(pa.fecha_fin_est_act_padre) = 1\n" +
                "    THEN ' tardo 1 dia en finalizar.'\n" +
                "  WHEN MAX(pa.fecha_fin_real_act_padre) - MAX(pa.fecha_fin_est_act_padre) > 0\n" +
                "    THEN ' tardo ' || MAX(pa.fecha_fin_real_act_padre) - MAX(pa.fecha_fin_est_act_padre) || ' dias en finalizar.'\n" +
                "  WHEN MAX(pa.fecha_fin_real_act_padre) - MAX(pa.fecha_fin_est_act_padre) = -1\n" +
                "    THEN ' finalizo un dia antes.'\n" +
                "  WHEN MAX(pa.fecha_fin_real_act_padre) - MAX(pa.fecha_fin_est_act_padre) < 0\n" +
                "    THEN ' finalizo ' || MAX(pa.fecha_fin_real_act_padre) - MAX(pa.fecha_fin_est_act_padre) || ' dias antes.'\n" +
                "  ELSE ' finalizo sin retraso.'\n" +
                "END AS TEXTO\n" +
                "FROM PROYECTO_ACTIVIDADES PA\n" +
                "WHERE PA.CODIGO_PROYECTO = ? \n" +
                "GROUP BY PA.COD_ACTIVIDAD_PADRE";

        try {
            Connection con = jdbcTemplate.getDataSource().getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, proyecto.getCodigoProyecto());

            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                oraciones.add(rs.getString("TEXTO"));
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oraciones;
    }
}
