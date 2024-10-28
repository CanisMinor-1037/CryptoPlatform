package com.platform.data.service.fabricService.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.platform.data.constant.ContractConstant;
import com.platform.data.entity.fabricDto.DataAssetFabricDto;
import com.platform.data.service.fabricService.DataAssetFabricService;
import org.hyperledger.fabric.client.CommitException;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.GatewayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;

@Service
public class DataAssetFabricServiceImpl implements DataAssetFabricService {
    @Value("${fabric.sync}")
    private boolean sync;
    @Autowired
    private Contract contract;
    @Autowired
    private Executor executor;
    @Override
    public void createDataAsset(String id,String name,String ownerId,String policy,String location,String field,String cid,String AESkey,int encType) throws GatewayException, CommitException {
        if(!sync){
            executor.execute(new Runnable() {
                public void run() {
                    try {
                        contract.submitTransaction(ContractConstant.CREATE_DATA_ASSET,id,name,ownerId,policy,location,field,cid,AESkey,String.valueOf(encType));
                    }
                    catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        else{
            contract.submitTransaction(ContractConstant.CREATE_DATA_ASSET,id,name,ownerId,policy,location,field,cid,AESkey,String.valueOf(encType));
        }
    }
    @Override
    public DataAssetFabricDto readDataAsset(String id) throws GatewayException {
        byte[] result = contract.evaluateTransaction(ContractConstant.READ_DATA_ASSET,id);
        String json = new String(result);
        return new Gson().fromJson(json, DataAssetFabricDto.class);
    }
    @Override
    public List<DataAssetFabricDto> getAllDataAsset() throws GatewayException {
        byte[] result = contract.evaluateTransaction(ContractConstant.GET_ALL_DATA_ASSET);
        String json = new String(result);
        return new Gson().fromJson(json, new TypeToken<List<DataAssetFabricDto>>() {}.getType());
    }
}
