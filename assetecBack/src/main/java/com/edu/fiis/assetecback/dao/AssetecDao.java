package com.edu.fiis.assetecback.dao;

import com.edu.fiis.assetecback.dto.*;
import com.edu.fiis.assetecback.dto.request.*;
import com.edu.fiis.assetecback.dto.responses.ReporteResponse;
import com.edu.fiis.assetecback.dto.responses.ResumenTrabajador;
import com.edu.fiis.assetecback.dto.responses.Rol;

import java.util.List;

public interface AssetecDao {
    public void crearActa(RegistroActa registroActa);
    List<Proyecto> traerProyectosUsuario(Persona persona);
    List<Alcance> obtenerAlcancesProyecto(Proyecto proyecto);
    void aceptarActa(RespuestaCliente respuestaCliente);
    List<Actividad> obtenerActividadesProyecto(Proyecto proyecto);
    List<Rol> obtenerListaRolesProyecto(Proyecto proyecto);
    void completarActividad(Actividad actividad);
    void rechazarActa(RespuestaCliente respuestaCliente);
    List<Objetivo> obtenerObjetivosProyecto(Proyecto proyecto);
    List<ObjetivoActividad> obtenerObjetivosActividad(Actividad actividad);
    List<Rol> obtenerListaRolesActividad(Actividad actividad);
    Asistencia obtenerTrabajadorActividad(TrabajadorActividad ta);
    void registrarAsistenciaTrabajador(Asistencia ra);
    List<ResumenTrabajador> obtenerResumenTrabajadorActividad(Actividad actividad);
    List<Asistencia> traerDatosTrabajadorActividad(Asistencia asistencia);
    void eliminarAsistenciaTrabajador(Asistencia asistencia);
    void registrarPago(RegistroPago registroPago);
    void registrarComprobantePago(RegistroComprobante registroComprobante);
    void generarCierre(Proyecto proyecto);
    List<ReporteResponse> traerReportesProyecto(Proyecto proyecto);
    List<String> reporteGastosActividad(Proyecto proyecto);
    List<String> reporteFechasProyecto(Proyecto proyecto);
    List<String> reporteFechasActividadesProyecto(Proyecto proyecto);
}
