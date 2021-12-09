package com.edu.fiis.assetecback.dto;

import lombok.Data;

@Data
public class Solicitud {
    private Integer codigoSolicitud;
    private String fechaSolicitud;
    private String fechaModificacion;
    private String estado;
    private String dni;
    private String codigoTipo;
}
