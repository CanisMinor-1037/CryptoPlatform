package com.platform.data.service.fabricService.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.platform.data.constant.ContractConstant;
import com.platform.data.entity.fabricDto.DBAssetFabricDto;
import com.platform.data.entity.fabricDto.DBAssetOrderFabricDto;
import com.platform.data.service.fabricService.DBAssetFabricService;
import org.hyperledger.fabric.client.CommitException;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.GatewayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;

@Service
public class DBAssetFabricServiceImpl implements DBAssetFabricService {
    @Value("${fabric.sync}")
    private boolean sync;
    @Autowired
    private Contract contract;
    @Autowired
    private Executor executor;
    @Override
    public void createDBAsset(String id, String name, String ownerId, String policy, String location, String field, String url, String username, String password, String AESkey, int encType) throws GatewayException, CommitException {
        if(!sync){
            executor.execute(new Runnable() {
                public void run() {
                    try {
                        contract.submitTransaction(ContractConstant.CREATE_DB_ASSET,id,name,ownerId,policy,location,field,url,username,password,AESkey,String.valueOf(encType));
                    }
                    catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        else{
            contract.submitTransaction(ContractConstant.CREATE_DB_ASSET,id,name,ownerId,policy,location,field,url,username,password,AESkey,String.valueOf(encType));
        }
    }

    @Override
    public DBAssetFabricDto readDBAsset(String id) throws GatewayException {
        byte[] result = contract.evaluateTransaction(ContractConstant.READ_DB_ASSET,id);
        String resultString = new String(result);
        return new Gson().fromJson(resultString, DBAssetFabricDto.class);
    }

    @Override
    public List<DBAssetFabricDto> getAllDBAsset() throws GatewayException {
        byte[] result = contract.evaluateTransaction(ContractConstant.GET_ALL_DB_ASSET);
        String resultString = new String(result);
        return new Gson().fromJson(resultString, new TypeToken<List<DBAssetFabricDto>>() {}.getType());
    }
}
