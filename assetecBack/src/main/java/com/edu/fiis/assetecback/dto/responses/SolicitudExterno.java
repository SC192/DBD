package com.edu.fiis.assetecback.dto.responses;


import lombok.Data;

@Data
public class SolicitudExterno {
    private Integer CodSolicitud;
    private String FechaSolicitud;
    private String ApellidosNombres;
    private String Ruc;
    private String Correo;

}
