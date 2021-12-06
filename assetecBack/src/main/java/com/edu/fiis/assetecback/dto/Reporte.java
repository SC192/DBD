package com.edu.fiis.assetecback.dto;

import lombok.Data;

@Data
public class Reporte {
    private String codigoProyecto;
    private Integer numeroReporte;
    private String fecha;
    private String descripcion;
    private String codigoTipo;
}
