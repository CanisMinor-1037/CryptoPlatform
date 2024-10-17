package com.platform.data.service.webService;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.data.entity.fabricDto.VideoAssetFabricDto;
import com.platform.data.entity.po.VideoAsset;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface VideoAssetService extends IService<VideoAsset> {
    void createByFabric(VideoAssetFabricDto videoAssetFabricDto);

    List<VideoAsset> videoAvailable();

    List<VideoAsset> ownervideo();
}
