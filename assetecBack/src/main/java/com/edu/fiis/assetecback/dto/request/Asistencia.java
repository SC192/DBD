package com.edu.fiis.assetecback.dto.request;

import com.edu.fiis.assetecback.dto.responses.ResumenTrabajador;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Asistencia extends ResumenTrabajador {
    private String codigoActividad;
    private Double horasTrabajadas;
    private String fecha;
    private String codigo;
}
