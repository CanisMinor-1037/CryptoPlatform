package com.platform.data.service.fabricService.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.platform.data.constant.ContractConstant;
import com.platform.data.entity.fabricDto.AttributeApplicationFabricDto;
import com.platform.data.service.fabricService.AttributeApplicationFabricService;
import org.hyperledger.fabric.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;

@Service
public class AttributeApplicationFabricServiceImpl implements AttributeApplicationFabricService {
    @Value("${fabric.sync}")
    private boolean sync;
    @Autowired
    private Contract contract;
    @Autowired
    private Executor executor;
    @Override
    public void createAttributeApplication(String id,String departmentId,String attribute,int status) throws GatewayException, CommitException {
        if(!sync){
            executor.execute(new Runnable() {
                public void run() {
                    try {
                        contract.submitTransaction(ContractConstant.CREATE_ATTRIBUTE_APPLICATION,id,departmentId,attribute,String.valueOf(status));
                    }
                    catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        else{
            contract.submitTransaction(ContractConstant.CREATE_ATTRIBUTE_APPLICATION,id,departmentId,attribute,String.valueOf(status));
        }
    }
    @Override
    public AttributeApplicationFabricDto readAttributeApplication(String id) throws GatewayException {
        byte[] result = contract.evaluateTransaction(ContractConstant.READ_ATTRIBUTE_APPLICATION,id);
        String resultString = new String(result);
        return new Gson().fromJson(resultString, AttributeApplicationFabricDto.class);
    }
    @Override
    public void updateAttributeApplicationStatus(String id,int status) throws GatewayException, CommitException {
        if(!sync){
            executor.execute(new Runnable() {
                public void run() {
                    try {
                        contract.submitTransaction(ContractConstant.UPDATE_ATTRIBUTE_APPLICATION_STATUS,id,String.valueOf(status));
                    }
                    catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        else{
            contract.submitTransaction(ContractConstant.UPDATE_ATTRIBUTE_APPLICATION_STATUS,id,String.valueOf(status));
        }
    }
    @Override
    public List<AttributeApplicationFabricDto> getAllAttributeApplication() throws GatewayException {
        byte[] result = contract.evaluateTransaction(ContractConstant.GET_ALL_ATTRIBUTE_APPLICATION);
        String json = new String(result);
        return new Gson().fromJson(json, new TypeToken<List<AttributeApplicationFabricDto>>() {}.getType());
    }
}
