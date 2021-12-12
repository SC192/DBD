package com.edu.fiis.assetecback.dto;

import lombok.Data;

@Data
public class Telefono {
    private Integer codigoTelefono;
    private String numero;
    private String prefijo;
    private String dni;

    public Telefono(String num, String pre, String dn){
        numero=num;
        prefijo=pre;
        dni=dn;
    }

    public Telefono() {

    }
}
