package com.edu.fiis.assetecback.dto;

import lombok.Data;

@Data
public class TrabajadorPerfilActividad {
    private Integer codigo;
    private String fecha;
    private Double cantidadHoras;
    private String codigoActividad;
    private String dni;
    private String codigoPerfil;
}
