package com.edu.fiis.assetecback.dao;

import com.edu.fiis.assetecback.dto.Actividad;
import com.edu.fiis.assetecback.dto.Alcance;
import com.edu.fiis.assetecback.dto.Persona;
import com.edu.fiis.assetecback.dto.Proyecto;
import com.edu.fiis.assetecback.dto.request.RegistroActa;
import com.edu.fiis.assetecback.dto.request.RespuestaCliente;
import com.edu.fiis.assetecback.dto.responses.Rol;

import java.util.List;

public interface AssetecDao {
    public void crearActa(RegistroActa registroActa);
    List<Proyecto> treaerProyectosUsuario(Persona persona);
    List<Alcance> obtenerAlcancesProyecto(Proyecto proyecto);
    void aceptarActa(RespuestaCliente respuestaCliente);
    List<Actividad> obtenerActividadesProyecto(Proyecto proyecto);
    List<Rol> obtenerListaRolesProyecto(Proyecto proyecto);
    void completarActividad(Actividad actividad);
    void rechazarActa(RespuestaCliente respuestaCliente);
}
