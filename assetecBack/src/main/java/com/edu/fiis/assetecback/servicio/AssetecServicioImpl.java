package com.edu.fiis.assetecback.servicio;

import com.edu.fiis.assetecback.dao.AssetecDao;
import com.edu.fiis.assetecback.dto.*;
import com.edu.fiis.assetecback.dto.request.*;
import com.edu.fiis.assetecback.dto.responses.ReporteResponse;
import com.edu.fiis.assetecback.dto.responses.ResumenTrabajador;
import com.edu.fiis.assetecback.dto.responses.Rol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AssetecServicioImpl implements AssetecServicio{
    @Autowired
    private AssetecDao dao;

    public void crearActa(RegistroActa registroActa) {
        dao.crearActa(registroActa);
    }

    public List<Proyecto> traerProyectosUsuario(Persona persona) {
        return dao.traerProyectosUsuario(persona);
    }

    public List<Alcance> obtenerAlcancesProyecto(Proyecto proyecto) {
        return dao.obtenerAlcancesProyecto(proyecto);
    }

    public void aceptarActa(RespuestaCliente respuestaCliente) {
        dao.aceptarActa(respuestaCliente);
    }

    public List<Actividad> obtenerActividadesProyecto(Proyecto proyecto){
        return dao.obtenerActividadesProyecto(proyecto);
    }

    public List<Rol> obtenerListaRolesProyecto(Proyecto proyecto) {
        return dao.obtenerListaRolesProyecto(proyecto);
    }

    public void completarActividad(Actividad actividad) {
        dao.completarActividad(actividad);
    }

    public void rechazarActa(RespuestaCliente respuestaCliente) {
        dao.rechazarActa(respuestaCliente);
    }

    public List<Objetivo> obtenerObjetivosProyecto(Proyecto proyecto) {
        return dao.obtenerObjetivosProyecto(proyecto);
    }

    public List<ObjetivoActividad> obtenerObjetivosActividad(Actividad actividad) {
        return dao.obtenerObjetivosActividad(actividad);
    }

    public List<Rol> obtenerListaRolesActividad(Actividad actividad) {
        return dao.obtenerListaRolesActividad(actividad);
    }

    public Asistencia obtenerTrabajadorActividad(TrabajadorActividad ta) {
        return dao.obtenerTrabajadorActividad(ta);
    }

    public void registrarAsistenciaTrabajador(Asistencia ra) {
        dao.registrarAsistenciaTrabajador(ra);
    }

    public List<ResumenTrabajador> obtenerResumenTrabajadorActividad(Actividad actividad) {
        return dao.obtenerResumenTrabajadorActividad(actividad);
    }

    public List<Asistencia> traerDatosTrabajadorActividad(Asistencia asistencia) {
        return dao.traerDatosTrabajadorActividad(asistencia);
    }

    public void eliminarAsistenciaTrabajador(Asistencia asistencia) {
        dao.eliminarAsistenciaTrabajador(asistencia);
    }

    public void registrarPago(RegistroPago registroPago) {
        dao.registrarPago(registroPago);
    }

    public void registrarComprobantePago(RegistroComprobante registroComprobante) {
        dao.registrarComprobantePago(registroComprobante);
    }

    public void generarCierre(Proyecto proyecto) {
        dao.generarCierre(proyecto);
    }

    public List<ReporteResponse> traerReportesProyecto(Proyecto proyecto) {
        return dao.traerReportesProyecto(proyecto);
    }

    public List<String> reporteGastosActividad(Proyecto proyecto) {
        return dao.reporteGastosActividad(proyecto);
    }

    public String reporteFechasProyecto(Proyecto proyecto) {
        return dao.reporteFechasProyecto(proyecto);
    }

    public List<String> reporteFechasActividadesProyecto(Proyecto proyecto) {
        return dao.reporteFechasActividadesProyecto(proyecto);
    }
}
