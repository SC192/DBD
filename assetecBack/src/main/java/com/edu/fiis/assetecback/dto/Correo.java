package com.edu.fiis.assetecback.dto;

import lombok.Data;

@Data
public class Correo {
    private Integer codigoCorreo;
    private String correo;
    private String dni;
    public Correo (String cor, String dn){
        correo = cor;
        dni = dn;
    }

    public Correo() {

    }
}
