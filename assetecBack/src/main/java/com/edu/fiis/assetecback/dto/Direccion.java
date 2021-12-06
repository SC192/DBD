package com.edu.fiis.assetecback.dto;

import lombok.Data;

@Data
public class Direccion {
    private String codigoDireccion;
    private String codigoPostal;
    private String nombre;
    private String unidad;
    private String distrito;
    private String provincia;
    private String pais;
}
