package com.edu.fiis.assetecback.dto.responses;

import lombok.Data;

@Data
public class SolicitudInternoRespuesta {
    private Integer CodSolicitud;
    private String FechaSolicitud;
    private String ApellidosNombres;
    private String Correo;
    private String Estado;
}
