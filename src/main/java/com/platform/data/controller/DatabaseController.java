package com.platform.data.controller;

import com.google.gson.JsonArray;
import com.platform.data.common.Response;
import com.platform.data.service.databaseService.SqlService;
import com.platform.data.service.fabricService.DBAssetOrderFabricService;
import com.platform.data.util.FabricIdUtil;
import com.platform.data.util.SqlUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hyperledger.fabric.client.CommitException;
import org.hyperledger.fabric.client.GatewayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@CrossOrigin(origins = "*")
public class DatabaseController {
    private final String DATABASE = "/database";
    private final String TEST_CONNECT = "/testConnect";
    private final String EXECUTE_QUERY = "/executeQuery";
    @Autowired
    private SqlService sqlService;
    @Autowired
    private DBAssetOrderFabricService dbAssetOrderFabricService;
    @ApiOperation("测试连接")
    @PostMapping(DATABASE + TEST_CONNECT)
    public Response<String> testConnect(String url,String username,String password){
        if(SqlUtil.testConnect(url,username,password)){
            return Response.success("ok");
        }
        else {
            return Response.fail("failed");
        }
    }
    @ApiOperation("数据库查询")
    @PostMapping(DATABASE + EXECUTE_QUERY)
    public Response<String> executeQuery(String url,String username,String password,String sql,String assetFabricId) throws SQLException, CommitException, GatewayException {
        JsonArray jsonArray = SqlUtil.executeQuery(url,username,password,sql);
        dbAssetOrderFabricService.createDBAssetOrder(FabricIdUtil.generateDBAssetOrderFabricId(),assetFabricId,FabricIdUtil.getMyFabricId(),sql);
        return Response.success(jsonArray.toString());
    }
}
