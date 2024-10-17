package com.platform.data.service.fabricService;

import com.platform.data.entity.fabricDto.DBAssetFabricDto;
import org.hyperledger.fabric.client.CommitException;
import org.hyperledger.fabric.client.GatewayException;

import java.util.List;

public interface DBAssetFabricService {
    void createDBAsset(String id,String name,String ownerId,String policy,String location,String field,String url,String username,String password,String AESkey,int encType) throws GatewayException, CommitException;
    DBAssetFabricDto readDBAsset(String id) throws GatewayException;
    List<DBAssetFabricDto> getAllDBAsset() throws GatewayException;
}
