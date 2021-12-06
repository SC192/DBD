package com.edu.fiis.assetecback.dto;

import lombok.Data;

@Data
public class ComprobantePago {
    private String numero;
    private Double importe;
    private String fecha;
    private String proveedor;
    private String descripcion;
    private String codigoMoneda;
    private String codigoTipoComprobante;
    private String codigoTipoGasto;
    private String codigoActividad;
}
