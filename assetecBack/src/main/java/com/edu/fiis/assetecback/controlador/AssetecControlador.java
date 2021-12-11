package com.edu.fiis.assetecback.controlador;

import com.edu.fiis.assetecback.dto.request.RegistroActa;
import com.edu.fiis.assetecback.dto.request.RespuestaCliente;
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
    public @ResponseBody List<Proyecto> treaerProyectosUsuario(@RequestBody Persona persona){
        return assetecServicio.treaerProyectosUsuario(persona);
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
}
