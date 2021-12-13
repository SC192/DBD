package com.edu.fiis.assetecback.dto.responses;

import com.edu.fiis.assetecback.dto.Persona;
import com.edu.fiis.assetecback.dto.Proyecto;
import lombok.Data;

@Data
public class ProyectoDetallado {
    private Proyecto proyecto;
    private Persona encargado;
    private Persona cliente;
}
