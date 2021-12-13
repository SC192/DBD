package com.edu.fiis.assetecback.servicio;

import com.edu.fiis.assetecback.dto.*;
import com.edu.fiis.assetecback.dto.request.*;
import com.edu.fiis.assetecback.dto.responses.*;

import java.util.List;

public interface AssetecServicio {
    /**Registro del usuario*/ /**Crear cuenta*/
    public abstract void enviarSolicitudRegistro (SolicitudRegistro solicitudRegistro);
    /**Registro del usuario*/ /**Revisar Solicitud*/
    public abstract List<SolicitudInterno> traerSolicitudesInterno(Buscar buscar);
    public abstract List<SolicitudExterno> traerSolicitudesExterno(Buscar buscar);
    public abstract void responderSolicitudInterno (SolicitudInternoRespuesta solicitudInternoRespuesta);
    public abstract void responderSolicitudExterno (SolicitudExternoRespuesta solicitudExternoRespuesta);
    /**Registro del usuario*/ /**Modificar Numeros*/
    public abstract List<Telefono> traerTelefonos (Buscar buscar);
    public abstract void agregarNumero(Telefono telefono);
    public abstract void quitarNumero(Telefono telefono);
    /**Registro del usuario*/ /**Modificar Correos*/
    public abstract List<Correo> traerCorreos (Buscar buscar);
    public abstract void agregarCorreoElectronico(Correo correo);
    public abstract void quitarCorreoElectronico(Correo correo);
    /**Ingreso del usuario*/ /**Ingresar*/
    public abstract Persona ingresarCuenta (Persona persona);
    /**Modificar detalles del rol*/ /**Asignar Perfil*/
    public abstract List<TrabajadorPerfilNombre> traerTrabajadorPerfiles (Buscar buscar);
    public abstract void agregarTrabajadorPerfil(TrabajadorPerfilNombre trabajadorPerfilNombre);
    public abstract void quitarTrabajadorPerfil(TrabajadorPerfilNombre trabajadorPerfilNombre);





    void crearActa(RegistroActa registroActa);
    List<ProyectoDetallado> traerProyectosUsuario(Persona persona);
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
    String reporteFechasProyecto(Proyecto proyecto);
    List<String> reporteFechasActividadesProyecto(Proyecto proyecto);
}
