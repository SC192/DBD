package com.edu.fiis.assetecback.dto.responses;

import lombok.Data;

@Data
public class ReporteResponse {
    private String fecha;
    private String descripcion;
    private Integer numeroReporte;
    private String tipoReporte;
}
