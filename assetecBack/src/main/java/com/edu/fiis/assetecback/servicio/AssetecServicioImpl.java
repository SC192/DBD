package com.edu.fiis.assetecback.servicio;

import com.edu.fiis.assetecback.dao.AssetecDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AssetecServicioImpl implements AssetecServicio{
    @Autowired
    private AssetecDao dao;
}
