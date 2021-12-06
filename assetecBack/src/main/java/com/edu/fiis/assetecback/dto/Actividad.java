package com.edu.fiis.assetecback.dto;

import lombok.Data;

@Data
public class Actividad {
    private String codigoProyecto;
    private String codigoActividad;
    private String codigoActividadPadre;
    private String nombre;
    private String descripcion;
    private String fechaInicioEstimada;
    private String fechaInicioReal;
    private String fechaFinEstimada;
    private String fechaFinReal;
    private String codigoGasto;
    private Double cantidadHoras;
    private Integer posicion;
}
