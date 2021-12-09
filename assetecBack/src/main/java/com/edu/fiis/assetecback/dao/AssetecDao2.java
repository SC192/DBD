package com.edu.fiis.assetecback.dao;


import com.edu.fiis.assetecback.dto.responses.SolicitudInterno;

import java.util.List;

public interface AssetecDao2 {

    List<SolicitudInterno> traerSolicitudesInterno(String ApellidosNombres);

}
