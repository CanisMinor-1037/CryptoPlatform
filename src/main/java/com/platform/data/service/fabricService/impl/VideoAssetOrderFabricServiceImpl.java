package com.platform.data.service.fabricService.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.platform.data.constant.ContractConstant;
import com.platform.data.entity.fabricDto.DBAssetOrderFabricDto;
import com.platform.data.entity.fabricDto.VideoAssetOrderFabricDto;
import com.platform.data.service.fabricService.VideoAssetOrderFabricService;
import org.hyperledger.fabric.client.CommitException;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.GatewayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;

@Service
public class VideoAssetOrderFabricServiceImpl implements VideoAssetOrderFabricService {
    @Value("${fabric.sync}")
    private boolean sync;
    @Autowired
    private Contract contract;
    @Autowired
    private Executor executor;
    @Override
    public void createVideoAssetOrder(String id, String dataAssetId, String applicantId, int status) throws GatewayException, CommitException {
        if(!sync){
            executor.execute(new Runnable() {
                public void run() {
                    try {
                        contract.submitTransaction(ContractConstant.CREATE_VIDEO_ASSET_ORDER,id,dataAssetId,applicantId,String.valueOf(status));
                    }
                    catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        else{
            contract.submitTransaction(ContractConstant.CREATE_VIDEO_ASSET_ORDER,id,dataAssetId,applicantId,String.valueOf(status));
        }
    }

    @Override
    public VideoAssetOrderFabricDto readVideoAssetOrder(String id) throws GatewayException {
        byte[] result = contract.evaluateTransaction(ContractConstant.READ_VIDEO_ASSET_ORDER,id);
        String resultString = new String(result);
        return new Gson().fromJson(resultString, VideoAssetOrderFabricDto.class);
    }

    @Override
    public void updateVideoAssetOrderStatus(String id, int status) throws GatewayException, CommitException {
        if(!sync){
            executor.execute(new Runnable() {
                public void run() {
                    try {
                        contract.submitTransaction(ContractConstant.UPDATE_VIDEO_ASSET_ORDER_STATUS,id,String.valueOf(status));
                    }
                    catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        else{
            contract.submitTransaction(ContractConstant.UPDATE_VIDEO_ASSET_ORDER_STATUS,id,String.valueOf(status));
        }
    }

    @Override
    public List<VideoAssetOrderFabricDto> getAllVideoAssetOrder() throws GatewayException {
        byte[] result = contract.evaluateTransaction(ContractConstant.GET_ALL_VIDEO_ASSET_ORDER);
        String resultString = new String(result);
        return new Gson().fromJson(resultString, new TypeToken<List<VideoAssetOrderFabricDto>>() {}.getType());
    }
}
