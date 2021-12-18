package com.edu.fiis.assetecback.servicio;

import com.edu.fiis.assetecback.dao.AssetecDao;
import com.edu.fiis.assetecback.dao.AssetecDao2;
import com.edu.fiis.assetecback.dto.*;
import com.edu.fiis.assetecback.dto.request.*;
import com.edu.fiis.assetecback.dto.responses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AssetecServicioImpl implements AssetecServicio{
    @Autowired
    private AssetecDao dao;
    private AssetecDao2 dao2;
    /**Registro del usuario*/ /**Crear cuenta*/
    public void enviarSolicitudRegistro (SolicitudRegistro solicitudRegistro){
        dao2.enviarSolicitudRegistro(solicitudRegistro);
    };
    /**Registro del usuario*/ /**Revisar Solicitud*/
    public List<SolicitudInterno> traerSolicitudesInterno(Buscar buscar){
        return dao2.traerSolicitudesInterno(buscar);
    };
    public List<SolicitudExterno> traerSolicitudesExterno(Buscar buscar){
        return dao2.traerSolicitudesExterno(buscar);
    };
    public void responderSolicitudInterno (SolicitudInternoRespuesta solicitudInternoRespuesta){
        dao2.responderSolicitudInterno(solicitudInternoRespuesta);
    };
    public void responderSolicitudExterno (SolicitudExternoRespuesta solicitudExternoRespuesta){
        dao2.responderSolicitudExterno(solicitudExternoRespuesta);
    };
    /**Registro del usuario*/ /**Modificar Numeros*/
    public List<Telefono> traerTelefonos (Buscar buscar){
        return dao2.traerTelefonos(buscar);
    };
    public void agregarNumero(Telefono telefono){
        dao2.agregarNumero(telefono);
    };
    public void quitarNumero(Telefono telefono){
        dao2.quitarNumero(telefono);
    };
    /**Registro del usuario*/ /**Modificar Correos*/
    public List<Correo> traerCorreos (Buscar buscar){
        return dao2.traerCorreos(buscar);
    };
    public void agregarCorreoElectronico(Correo correo){
        dao2.agregarCorreoElectronico(correo);
    };
    public void quitarCorreoElectronico(Correo correo){
        dao2.quitarCorreoElectronico(correo);
    };
    /**Ingreso del usuario*/ /**Ingresar*/
    public Persona ingresarCuenta (Persona persona){
        return dao2.ingresarCuenta(persona);
    };
    /**Modificar detalles del rol*/ /**Asignar Perfil*/
    public List<TrabajadorPerfilNombre> traerTrabajadorPerfiles (Buscar buscar){
        return dao2.traerTrabajadorPerfiles(buscar);
    };
    public void agregarTrabajadorPerfil(TrabajadorPerfilNombre trabajadorPerfilNombre){
        dao2.agregarTrabajadorPerfil(trabajadorPerfilNombre);
    };
    public void quitarTrabajadorPerfil(TrabajadorPerfilNombre trabajadorPerfilNombre){
        dao2.quitarTrabajadorPerfil(trabajadorPerfilNombre);
    };





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
