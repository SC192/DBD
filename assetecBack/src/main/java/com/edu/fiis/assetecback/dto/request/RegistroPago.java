package com.edu.fiis.assetecback.dto.request;

import lombok.Data;

@Data
public class RegistroPago {
    private Double maximaRemumeracion;
    private String nombreMoneda;
    private String codigoActividad;
}
