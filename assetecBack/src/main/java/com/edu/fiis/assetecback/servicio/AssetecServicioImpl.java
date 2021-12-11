package com.edu.fiis.assetecback.servicio;

import com.edu.fiis.assetecback.dao.AssetecDao;
import com.edu.fiis.assetecback.dto.Actividad;
import com.edu.fiis.assetecback.dto.Alcance;
import com.edu.fiis.assetecback.dto.Persona;
import com.edu.fiis.assetecback.dto.Proyecto;
import com.edu.fiis.assetecback.dto.request.RegistroActa;
import com.edu.fiis.assetecback.dto.request.RespuestaCliente;
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

    public List<Proyecto> treaerProyectosUsuario(Persona persona) {
        return dao.treaerProyectosUsuario(persona);
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
}
