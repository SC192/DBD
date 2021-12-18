package com.edu.fiis.assetecback.controlador;

import com.edu.fiis.assetecback.dto.request.*;
import com.edu.fiis.assetecback.dto.responses.*;
import com.edu.fiis.assetecback.servicio.AssetecServicio;
import com.edu.fiis.assetecback.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
public class AssetecControlador {
    @Autowired
    private AssetecServicio assetecServicio;

    /**Registro del usuario*/ /**Crear cuenta*/
    @RequestMapping(
            value = "/enviar-solicitud-registro",
            produces = "application/json;charset=utf-8",
            method =RequestMethod.POST
    )
    public @ResponseBody void enviarSolicitudRegistro (@RequestBody SolicitudRegistro solicitudRegistro){
        assetecServicio.enviarSolicitudRegistro(solicitudRegistro);
    };
    /**Registro del usuario*/ /**Revisar Solicitud*/
    @RequestMapping(
            value = "/traer-solicitudes-interno",
            produces = "application/json;charset=utf-8",
            method =RequestMethod.POST
    )
    public @ResponseBody List<SolicitudInterno> traerSolicitudesInterno(@RequestBody Buscar buscar){
        return assetecServicio.traerSolicitudesInterno(buscar);
    };
    @RequestMapping(
            value = "/traer-solicitudes-externo",
            produces = "application/json;charset=utf-8",
            method =RequestMethod.POST
    )
    public @ResponseBody List<SolicitudExterno> traerSolicitudesExterno(@RequestBody Buscar buscar){
        return assetecServicio.traerSolicitudesExterno(buscar);
    };
    @RequestMapping(
            value = "/responder-solicitud-interno",
            produces = "application/json;charset=utf-8",
            method =RequestMethod.POST
    )
    public @ResponseBody void responderSolicitudInterno (@RequestBody SolicitudInternoRespuesta solicitudInternoRespuesta){
        assetecServicio.responderSolicitudInterno(solicitudInternoRespuesta);
    };
    @RequestMapping(
            value = "/responder-solicitud-externo",
            produces = "application/json;charset=utf-8",
            method =RequestMethod.POST
    )
    public @ResponseBody void responderSolicitudExterno (@RequestBody SolicitudExternoRespuesta solicitudExternoRespuesta){
        assetecServicio.responderSolicitudExterno(solicitudExternoRespuesta);
    };
    /**Registro del usuario*/ /**Modificar Numeros*/
    @RequestMapping(
            value = "/traer-telefonos",
            produces = "application/json;charset=utf-8",
            method =RequestMethod.POST
    )
    public @ResponseBody List<Telefono> traerTelefonos (@RequestBody Buscar buscar){
        return assetecServicio.traerTelefonos(buscar);
    };
    @RequestMapping(
            value = "/agregar-numero",
            produces = "application/json;charset=utf-8",
            method =RequestMethod.POST
    )
    public @ResponseBody void agregarNumero(@RequestBody Telefono telefono){
        assetecServicio.agregarNumero(telefono);
    };
    @RequestMapping(
            value = "/quitar-numero",
            produces = "application/json;charset=utf-8",
            method =RequestMethod.POST
    )
    public @ResponseBody void quitarNumero(@RequestBody Telefono telefono){
        assetecServicio.quitarNumero(telefono);
    };
    /**Registro del usuario*/ /**Modificar Correos*/
    @RequestMapping(
            value = "/traer-correo",
            produces = "application/json;charset=utf-8",
            method =RequestMethod.POST
    )
    public @ResponseBody List<Correo> traerCorreos (@RequestBody Buscar buscar){
        return assetecServicio.traerCorreos(buscar);
    };
    @RequestMapping(
            value = "/agregar-correo-electronico",
            produces = "application/json;charset=utf-8",
            method =RequestMethod.POST
    )
    public @ResponseBody void agregarCorreoElectronico(@RequestBody Correo correo){
        assetecServicio.agregarCorreoElectronico(correo);
    };
    @RequestMapping(
            value = "/quitar-correo-electronico",
            produces = "application/json;charset=utf-8",
            method =RequestMethod.POST
    )
    public @ResponseBody void quitarCorreoElectronico(@RequestBody Correo correo){
        assetecServicio.quitarCorreoElectronico(correo);
    };
    /**Ingreso del usuario*/ /**Ingresar*/
    @RequestMapping(
            value = "/ingresar-cuenta",
            produces = "application/json;charset=utf-8",
            method =RequestMethod.POST
    )
    public @ResponseBody Persona ingresarCuenta (@RequestBody Persona persona){
        return assetecServicio.ingresarCuenta(persona);
    };
    /**Modificar detalles del rol*/ /**Asignar Perfil*/
    @RequestMapping(
            value = "/traer-trabajador-perfiles",
            produces = "application/json;charset=utf-8",
            method =RequestMethod.POST
    )
    public List<TrabajadorPerfilNombre> traerTrabajadorPerfiles (@RequestBody Buscar buscar){
        return assetecServicio.traerTrabajadorPerfiles(buscar);
    };
    @RequestMapping(
            value = "/agregar-trabajador-perfil",
            produces = "application/json;charset=utf-8",
            method =RequestMethod.POST
    )
    public @ResponseBody void agregarTrabajadorPerfil(@RequestBody TrabajadorPerfilNombre trabajadorPerfilNombre){
        assetecServicio.agregarTrabajadorPerfil(trabajadorPerfilNombre);
    };
    @RequestMapping(
            value = "/quitar-trabajador-perfil",
            produces = "application/json;charset=utf-8",
            method =RequestMethod.POST
    )
    public @ResponseBody void quitarTrabajadorPerfil(@RequestBody TrabajadorPerfilNombre trabajadorPerfilNombre){
        assetecServicio.quitarTrabajadorPerfil(trabajadorPerfilNombre);
    };



    @RequestMapping(
            value = "/iniciar-sesion",
            produces = "application/json;charset=utf-8",
            method =RequestMethod.POST
    )
    public @ResponseBody
    Proyecto iniciarSesion(@RequestBody String codigoProyecto) {
        return new Proyecto();
    }

    @RequestMapping(
            value = "/crear-acta",
            produces = "application/json;charset=utf-8",
            method =RequestMethod.POST
    )
    public void crearActa(@RequestBody RegistroActa registroActa) {
        assetecServicio.crearActa(registroActa);
    }

    @RequestMapping(
            value = "/traer-proyectos-usuario",
            produces = "application/json;charset=utf-8",
            method =RequestMethod.POST
    )
    public @ResponseBody List<Proyecto> traerProyectosUsuario(@RequestBody Persona persona){
        return assetecServicio.traerProyectosUsuario(persona);
    }

    @RequestMapping(
            value = "/obtener-alcances-proyecto",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody List<Alcance> obtenerAlcancesProyecto(@RequestBody Proyecto proyecto) {
        return assetecServicio.obtenerAlcancesProyecto(proyecto);
    }

    @RequestMapping(
            value = "/aceptar-acta",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public void aceptarActa(@RequestBody RespuestaCliente respuestaCliente) {
        assetecServicio.aceptarActa(respuestaCliente);
    }

    @RequestMapping(
            value = "/obtener-actividades-proyecto",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody List<Actividad> obtenerActividadesProyecto(@RequestBody Proyecto proyecto) {
        return assetecServicio.obtenerActividadesProyecto(proyecto);
    }

    @RequestMapping(
            value = "/obtener-roles-proyecto",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody List<Rol> obtenerListaRolesProyecto(@RequestBody Proyecto proyecto){
        return assetecServicio.obtenerListaRolesProyecto(proyecto);
    }
    @RequestMapping(
            value = "/completar-actividad",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public void completarActividad(@RequestBody Actividad actividad) {
        assetecServicio.completarActividad(actividad);
    }

    @RequestMapping(
            value = "/rechazar-acta",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public void rechazarActa(@RequestBody RespuestaCliente respuestaCliente) {
        assetecServicio.rechazarActa(respuestaCliente);
    }

    @RequestMapping(
            value = "/obtener-objetivos-proyecto",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody List<Objetivo> obtenerObjetivosProyecto(@RequestBody Proyecto proyecto) {
        return assetecServicio.obtenerObjetivosProyecto(proyecto);
    }

    @RequestMapping(
            value = "/obtener-objetivos-actividad",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody List<ObjetivoActividad> obtenerObjetivosActividad(@RequestBody Actividad actividad) {
        return assetecServicio.obtenerObjetivosActividad(actividad);
    }

    @RequestMapping(
            value = "/obtener-roles-actividad",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody List<Rol> obtenerListaRolesActividad(@RequestBody Actividad actividad) {
        return assetecServicio.obtenerListaRolesActividad(actividad);
    }

    @RequestMapping(
            value = "/obtener-trabajador-actividad",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody Asistencia obtenerTrabajadorActividad(@RequestBody TrabajadorActividad ta) {
        return assetecServicio.obtenerTrabajadorActividad(ta);
    }

    @RequestMapping(
            value = "/registrar-asistencia-trabajador",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public void registrarAsistenciaTrabajador(@RequestBody Asistencia ra) {
        assetecServicio.registrarAsistenciaTrabajador(ra);
    }

    @RequestMapping(
            value = "/obtener-resumen-trabajador-actividad",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody List<ResumenTrabajador> obtenerResumenTrabajadorActividad(@RequestBody Actividad actividad) {
        return assetecServicio.obtenerResumenTrabajadorActividad(actividad);
    }

    @RequestMapping(
            value = "/traer-datos-trabajador-actividad",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody List<Asistencia> traerDatosTrabajadorActividad(@RequestBody Asistencia asistencia) {
        return assetecServicio.traerDatosTrabajadorActividad(asistencia);
    }

    @RequestMapping(
            value = "/eliminar-asistencia-trabajador",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public void eliminarAsistenciaTrabajador(@RequestBody Asistencia asistencia) {
        assetecServicio.eliminarAsistenciaTrabajador(asistencia);
    }

    @RequestMapping(
            value = "/registrar-pago",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public void registrarPago(@RequestBody RegistroPago registroPago) {
        assetecServicio.registrarPago(registroPago);
    }

    @RequestMapping(
            value = "/registrar-comprobante-pago",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public void registrarComprobantePago(@RequestBody RegistroComprobante registroComprobante) {
        assetecServicio.registrarComprobantePago(registroComprobante);
    }

    @RequestMapping(
            value = "/generar-cierre",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public void generarCierre(@RequestBody Proyecto proyecto) {
        assetecServicio.generarCierre(proyecto);
    }

    @RequestMapping(
            value = "/traer-reportes-proyecto",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody List<ReporteResponse> traerReportesProyecto(@RequestBody Proyecto proyecto) {
        return assetecServicio.traerReportesProyecto(proyecto);
    }

    @RequestMapping(
            value = "/reporte-gastos-actividad",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody List<String> reporteGastosActividad(@RequestBody Proyecto proyecto) {
        return assetecServicio.reporteGastosActividad(proyecto);
    }

    @RequestMapping(
            value = "/reporte-fechas-proyecto",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody List<String> reporteFechasProyecto(@RequestBody Proyecto proyecto) {
        return assetecServicio.reporteFechasProyecto(proyecto);
    }

    @RequestMapping(
            value = "/reporte-fechas-actividad-proyecto",
            produces = "application/json;charset=utf-8",
            method = RequestMethod.POST
    )
    public @ResponseBody List<String> reporteFechasActividadesProyecto(@RequestBody Proyecto proyecto) {
        return assetecServicio.reporteFechasActividadesProyecto(proyecto);
    }
}
