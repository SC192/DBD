package com.edu.fiis.assetecback.dto.request;

import lombok.Data;

import java.util.List;
@Data
public class RegistroActa {
    private List<String> acuerdos;
    private String codigoProyecto;
}
