package com.platform.data.service.fabricService;

import com.platform.data.entity.fabricDto.VideoAssetFabricDto;
import org.hyperledger.fabric.client.CommitException;
import org.hyperledger.fabric.client.GatewayException;

import java.util.List;

public interface VideoAssetFabricService {
    void createVideoAsset(String id,String name,String ownerId,String policy,String location,String field,String rtspUrl,String AESkey,int encType) throws GatewayException, CommitException;
    VideoAssetFabricDto readVideoAsset(String id) throws GatewayException;
    List<VideoAssetFabricDto> getAllVideoAsset() throws GatewayException;
}
