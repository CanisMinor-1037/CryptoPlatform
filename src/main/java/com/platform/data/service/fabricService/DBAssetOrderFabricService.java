package com.platform.data.service.fabricService;

import com.platform.data.entity.fabricDto.DBAssetOrderFabricDto;
import org.hyperledger.fabric.client.CommitException;
import org.hyperledger.fabric.client.GatewayException;

import java.util.List;

public interface DBAssetOrderFabricService {
    void createDBAssetOrder(String id,String dataAssetId,String applicantId,String sql) throws GatewayException, CommitException;
    DBAssetOrderFabricDto readDBAssetOrder(String id) throws GatewayException;
    List<DBAssetOrderFabricDto> getAllDBAssetOrder() throws GatewayException;

}
