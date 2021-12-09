package com.edu.fiis.assetecback.dao;


import com.edu.fiis.assetecback.dto.request.Buscar;
import com.edu.fiis.assetecback.dto.responses.SolicitudExterno;
import com.edu.fiis.assetecback.dto.responses.SolicitudInterno;

import java.util.List;

public interface AssetecDao2 {

    public abstract List<SolicitudInterno> traerSolicitudesInterno(Buscar buscar);
    public abstract List<SolicitudExterno> traerSolicitudesExterno(Buscar buscar);

}
