package com.platform.data.service.databaseService;

import com.platform.data.entity.dto.SQLResult;

import java.util.List;

public interface SqlService {
    boolean testConnect(String url, String username, String password);
    SQLResult query(String url, String username, String password,String sql);
    int execute(String url, String username, String password,String sql);
}
