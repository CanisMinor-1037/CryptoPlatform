package com.platform.data.service.fabricService;

import com.platform.data.entity.fabricDto.DataAssetOrderFabricDto;
import org.hyperledger.fabric.client.CommitException;
import org.hyperledger.fabric.client.GatewayException;

import java.util.List;

public interface DataAssetOrderFabricService {
    void createDataAssetOrder(String id,String dataAssetId,String applicantId,int status) throws GatewayException, CommitException;
    DataAssetOrderFabricDto readDataAssetOrder(String id) throws GatewayException;
    void updateDataAssetOrderStatus(String id,int status) throws GatewayException, CommitException;

    List<DataAssetOrderFabricDto> getAllDataAssetOrder() throws GatewayException;
}
