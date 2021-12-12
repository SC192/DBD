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

    public Persona(String dn, String primerN, String apellidoM, String apellidoP, String cont, String fir) {
        dni = dn;
        primerNombre = primerN;
        apellidoMaterno = apellidoM;
        apellidoPaterno = apellidoP;
        contrasenia = cont;
        firma = fir;
    }
}


