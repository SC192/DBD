package com.edu.fiis.assetecback.dto;

import lombok.Data;

@Data
public class Proyecto {
    private String codigoProyecto;
    private String nombre;
    private String estado;
    private String descripcion;
    private String fechaFinReal;
    private String fechaFinEstimada;
    private String fechaInicioReal;
    private String fechaInicioEstimada;
    private String codigoPresupuesto;
}
