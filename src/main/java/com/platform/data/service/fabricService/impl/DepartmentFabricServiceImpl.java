package com.platform.data.service.fabricService.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.platform.data.constant.ContractConstant;
import com.platform.data.entity.fabricDto.AttributeItemFabricDto;
import com.platform.data.entity.fabricDto.DepartmentFabricDto;
import com.platform.data.service.fabricService.DepartmentFabricService;
import org.hyperledger.fabric.client.CommitException;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.GatewayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;

@Service
public class DepartmentFabricServiceImpl implements DepartmentFabricService {
    @Value("${fabric.sync}")
    private boolean sync;
    @Autowired
    private Contract contract;
    @Autowired
    private Executor executor;
    @Override
    public void createDepartment(String id,String name,String attribute) throws GatewayException, CommitException {
        if(!sync){
            executor.execute(new Runnable() {
                public void run() {
                    try {
                        contract.submitTransaction(ContractConstant.CREATE_DEPARTMENT,id,name,attribute);
                    }
                    catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        else{
            contract.submitTransaction(ContractConstant.CREATE_DEPARTMENT,id,name,attribute);
        }
    }
    @Override
    public DepartmentFabricDto readDepartment(String id) throws GatewayException {
        byte[] result = contract.evaluateTransaction(ContractConstant.READ_DEPARTMENT,id);
        String resultString = new String(result);
        return new Gson().fromJson(resultString, DepartmentFabricDto.class);
    }
    @Override
    public void updateDepartmentAttribute(String id,String attribute) throws GatewayException, CommitException {
        if(!sync){
            executor.execute(new Runnable() {
                public void run() {
                    try {
                        contract.submitTransaction(ContractConstant.UPDATE_DEPARTMENT_ATTRIBUTE,id,attribute);
                    }
                    catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        else{
            contract.submitTransaction(ContractConstant.UPDATE_DEPARTMENT_ATTRIBUTE,id,attribute);
        }
    }
    @Override
    public List<DepartmentFabricDto> getAllDepartments() throws GatewayException{
        byte[] result = contract.evaluateTransaction(ContractConstant.GET_ALL_DEPARTMENT);
        String resultString = new String(result);
        List<DepartmentFabricDto> allDepartments = new Gson().fromJson(resultString, new TypeToken<List<DepartmentFabricDto>>() {}.getType());
        return allDepartments;
    }
}
