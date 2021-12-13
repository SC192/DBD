package com.edu.fiis.assetecback.dao;


import com.edu.fiis.assetecback.dto.Correo;
import com.edu.fiis.assetecback.dto.Persona;
import com.edu.fiis.assetecback.dto.Telefono;
import com.edu.fiis.assetecback.dto.request.Buscar;
import com.edu.fiis.assetecback.dto.request.SolicitudExternoRespuesta;
import com.edu.fiis.assetecback.dto.request.SolicitudInternoRespuesta;
import com.edu.fiis.assetecback.dto.request.SolicitudRegistro;
import com.edu.fiis.assetecback.dto.responses.SolicitudExterno;
import com.edu.fiis.assetecback.dto.responses.SolicitudInterno;
import com.edu.fiis.assetecback.dto.responses.TrabajadorPerfilNombre;

import java.util.List;

public interface AssetecDao2 {

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

}
