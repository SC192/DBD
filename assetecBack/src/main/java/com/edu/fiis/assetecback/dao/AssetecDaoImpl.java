package com.edu.fiis.assetecback.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AssetecDaoImpl implements AssetecDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;
}
