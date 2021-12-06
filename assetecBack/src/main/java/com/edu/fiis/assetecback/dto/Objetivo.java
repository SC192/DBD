package com.edu.fiis.assetecback.dto;

import lombok.Data;

@Data
public class Objetivo {
    private Integer posicion;
    private String codigoProyecto;
    private String descripcion;
    private Integer posicionPadre;
    private String codigoProyectoPadre;
}
