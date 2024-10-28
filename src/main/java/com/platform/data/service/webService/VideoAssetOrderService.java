package com.platform.data.service.webService;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.data.entity.fabricDto.VideoAssetOrderFabricDto;
import com.platform.data.entity.po.DBAssetOrder;
import com.platform.data.entity.po.VideoAssetOrder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface VideoAssetOrderService extends IService<VideoAssetOrder> {
    void createByFabric(VideoAssetOrderFabricDto videoAssetOrderFabricDto);

    void updateByFabric(VideoAssetOrderFabricDto videoAssetOrderFabricDto);

    List<VideoAssetOrder> videoApplyHistory();

    List<VideoAssetOrder> videoAppliedHistory();

    List<Long> applyCountByEachMonth();

    List<Long> appliedCountByEachMonth();
}
