package com.edu.fiis.assetecback.dto.request;

import lombok.Data;

@Data
public class RegistroComprobante {
    private String numero;
    private Double importe;
    private String proveedor;
    private String fecha;
    private String descripcion;
    private String nombreMoneda;
    private String nombreTipoComprobante;
    private String nombreTipoGasto;
    private String codigoActividad;
}
