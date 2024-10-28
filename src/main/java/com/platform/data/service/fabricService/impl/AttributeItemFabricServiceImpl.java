package com.platform.data.service.fabricService.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.platform.data.constant.ContractConstant;
import com.platform.data.entity.fabricDto.AttributeItemFabricDto;
import com.platform.data.service.fabricService.AttributeItemFabricService;
import org.hyperledger.fabric.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

@Service
public class AttributeItemFabricServiceImpl implements AttributeItemFabricService {
    @Value("${fabric.sync}")
    private boolean sync;
    @Autowired
    private Contract contract;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private Executor executor;
    @Override
    public void createAttributeItem(String id, String content) throws EndorseException, CommitException, SubmitException, CommitStatusException {
        if(!sync){
            executor.execute(new Runnable() {
                public void run() {
                    try {
                        contract.submitTransaction(ContractConstant.CREATE_ATTRIBUTE_ITEM,id,content);
                    }
                    catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        else{
            contract.submitTransaction(ContractConstant.CREATE_ATTRIBUTE_ITEM,id,content);
        }
    }
    @Override
    public void deleteAttributeItem(String id) throws EndorseException, CommitException, SubmitException, CommitStatusException {
        if(!sync){
            executor.execute(new Runnable() {
                public void run() {
                    try {
                        contract.submitTransaction(ContractConstant.DELETE_ATTRIBUTE_ITEM,id);
                    }
                    catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        else{
            contract.submitTransaction(ContractConstant.DELETE_ATTRIBUTE_ITEM,id);
        }
    }
    @Override
    public List<AttributeItemFabricDto> getAllAttributeItem() throws GatewayException {
        String allAttributesString;
        byte[] result = contract.evaluateTransaction(ContractConstant.GET_ALL_ATTRIBUTE_ITEM);
        allAttributesString = new String(result);
        List<AttributeItemFabricDto> allAttributes = new Gson().fromJson(allAttributesString, new TypeToken<List<AttributeItemFabricDto>>() {}.getType());
        return allAttributes;
    }
    @Override
    public List<String> getAllAttributeItemString() throws GatewayException {
        List<AttributeItemFabricDto> allAttributes = getAllAttributeItem();
        return allAttributes.stream().map(item->item.getContent()).distinct().collect(Collectors.toList());
    }
    @Override
    public AttributeItemFabricDto readAttributeItem(String id) throws GatewayException {
        byte[] result = contract.evaluateTransaction(ContractConstant.READ_ATTRIBUTE_ITEM,id);
        String resultString = new String(result);
        return new Gson().fromJson(resultString, AttributeItemFabricDto.class);
    }
}
