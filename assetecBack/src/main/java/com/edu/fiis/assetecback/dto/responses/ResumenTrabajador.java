package com.edu.fiis.assetecback.dto.responses;

import lombok.Data;

@Data
public class ResumenTrabajador {
    private String nombreRol;
    private Double costoHora;
    private String dni;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private Double horasTotales;
}
