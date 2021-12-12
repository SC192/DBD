package com.edu.fiis.assetecback.dto;

import lombok.Data;

@Data
public class Direccion {
    private Integer codigoDireccion;
    private String codigoPostal;
    private String direccion;
    private String unidad;
    private String distrito;
    private String provincia;
    private String pais;

    public Direccion(String codPos, String dir, String uni, String dis, String pro, String pai){
        codigoPostal = codPos;
        direccion = dir;
        unidad = uni;
        distrito = dis;
        provincia = pro;
        pais = pai;
    }

    public Direccion() {

    }
}
