package com.edu.fiis.assetecback.dto.request;

import lombok.Data;
@Data
public class SolicitudExternoRespuesta {
    private Integer CodSolicitud;
    private String FechaSolicitud;
    private String ApellidosNombres;
    private String Ruc;
    private String Correo;
    private String Estado;

}
