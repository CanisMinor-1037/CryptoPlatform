package com.platform.data.service.fabricService;

import com.platform.data.entity.fabricDto.DataAssetFabricDto;
import org.hyperledger.fabric.client.CommitException;
import org.hyperledger.fabric.client.GatewayException;

import java.util.List;

public interface DataAssetFabricService {
    void createDataAsset(String id,String name,String ownerId,String policy,String location,String field,String cid,String AESkey,int encType) throws GatewayException, CommitException;
    DataAssetFabricDto readDataAsset(String id) throws GatewayException;

    List<DataAssetFabricDto> getAllDataAsset() throws GatewayException;
}
