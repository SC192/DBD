package com.edu.fiis.assetecback.controlador;

import com.edu.fiis.assetecback.dto.request.*;
import com.edu.fiis.assetecback.dto.responses.ProyectoDetallado;
import com.edu.fiis.assetecback.dto.responses.ReporteResponse;
import com.edu.fiis.assetecback.dto.responses.ResumenTrabajador;
import com.edu.fiis.assetecback.dto.responses.Rol;
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

    @RequestMapping(
            value = "/iniciar-sesion",
            produces = "application/json;charset=utf-8",
            method =RequestMethod.POST
    )
    public @ResponseBody Proyecto iniciarSesion(@RequestBody String codigoProyecto) {
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
    public @ResponseBody List<ProyectoDetallado> traerProyectosUsuario(@RequestBody Persona persona){
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
    public @ResponseBody String reporteFechasProyecto(@RequestBody Proyecto proyecto) {
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
