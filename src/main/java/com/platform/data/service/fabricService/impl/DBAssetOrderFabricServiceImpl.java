package com.platform.data.service.fabricService.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.platform.data.constant.ContractConstant;
import com.platform.data.entity.fabricDto.DBAssetOrderFabricDto;
import com.platform.data.entity.fabricDto.DepartmentFabricDto;
import com.platform.data.entity.po.DBAssetOrder;
import com.platform.data.entity.po.OrderHistory;
import com.platform.data.service.fabricService.DBAssetOrderFabricService;
import org.hyperledger.fabric.client.CommitException;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.GatewayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

@Service
public class DBAssetOrderFabricServiceImpl implements DBAssetOrderFabricService {
    @Value("${fabric.sync}")
    private boolean sync;
    @Autowired
    private Contract contract;
    @Autowired
    private Executor executor;
    @Override
    public void createDBAssetOrder(String id, String dataAssetId, String applicantId, String sql) throws GatewayException, CommitException {
        if(!sync){
            executor.execute(new Runnable() {
                public void run() {
                    try {
                        contract.submitTransaction(ContractConstant.CREATE_DB_ASSET_ORDER,id,dataAssetId,applicantId,sql);
                    }
                    catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        else{
            contract.submitTransaction(ContractConstant.CREATE_DB_ASSET_ORDER,id,dataAssetId,applicantId,sql);
        }
    }

    @Override
    public DBAssetOrderFabricDto readDBAssetOrder(String id) throws GatewayException {
        byte[] result = contract.evaluateTransaction(ContractConstant.READ_DB_ASSET_ORDER,id);
        String resultString = new String(result);
        return new Gson().fromJson(resultString, DBAssetOrderFabricDto.class);
    }

    @Override
    public List<DBAssetOrderFabricDto> getAllDBAssetOrder() throws GatewayException {
        byte[] result = contract.evaluateTransaction(ContractConstant.GET_ALL_DB_ASSET_ORDER);
        String resultString = new String(result);
        return new Gson().fromJson(resultString, new TypeToken<List<DBAssetOrderFabricDto>>() {}.getType());
    }
}
