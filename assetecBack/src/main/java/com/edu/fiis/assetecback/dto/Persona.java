package com.edu.fiis.assetecback.dto;

import lombok.Data;

@Data
public class Persona {
    private String dni;
    private String primerNombre;
    private String apellidoMaterno;
    private String apellidoPaterno;
    private String contrasenia;
    private String firma;
    private Integer codigoDireccion;
}
