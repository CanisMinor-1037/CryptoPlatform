package com.platform.data.service.databaseService.impl;

import com.platform.data.entity.dto.SQLResult;
import com.platform.data.service.databaseService.SqlService;
import org.springframework.stereotype.Service;

import java.sql.Driver;

@Service
public class SqlServiceImpl implements SqlService {
    @Override
    public boolean testConnect(String url, String username, String password) {
        return false;
    }

    @Override
    public SQLResult query(String url, String username, String password, String sql) {
        return null;
    }

    @Override
    public int execute(String url, String username, String password, String sql) {
        return 0;
    }
}
