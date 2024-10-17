package com.platform.data.service.fabricService;

import com.platform.data.entity.fabricDto.VideoAssetOrderFabricDto;
import org.hyperledger.fabric.client.CommitException;
import org.hyperledger.fabric.client.GatewayException;

import java.util.List;

public interface VideoAssetOrderFabricService {
    void createVideoAssetOrder(String id,String dataAssetId,String applicantId,int status) throws GatewayException, CommitException;
    VideoAssetOrderFabricDto readVideoAssetOrder(String id) throws GatewayException;
    void updateVideoAssetOrderStatus(String id,int status) throws GatewayException, CommitException;
    List<VideoAssetOrderFabricDto> getAllVideoAssetOrder() throws GatewayException;
}
