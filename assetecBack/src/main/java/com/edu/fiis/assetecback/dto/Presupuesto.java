package com.edu.fiis.assetecback.dto;

import lombok.Data;

@Data
public class Presupuesto {
    private String codigoPresupuesto;
    private Double costoEstimado;
    private Moneda moneda;
}
