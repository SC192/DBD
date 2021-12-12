package com.edu.fiis.assetecback.dto;

import lombok.Data;

@Data
public class Contacto {
    private String dni;
    private String ruc;
    public Contacto(String dn, String ru){
        dni = dn;
        ruc = ru;
    }
}
