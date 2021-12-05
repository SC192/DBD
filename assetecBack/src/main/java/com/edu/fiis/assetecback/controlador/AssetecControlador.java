package com.edu.fiis.assetecback.controlador;

import com.edu.fiis.assetecback.servicio.AssetecServicio;
import com.edu.fiis.assetecback.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
