package com.platform.data.service.webService;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.data.entity.fabricDto.DataAssetOrderFabricDto;
import com.platform.data.entity.po.Asset;
import com.platform.data.entity.po.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface OrderService extends IService<Order> {

    List<Order> fileApplyHistory();

    List<Order> fileAppliedHistory();

    boolean isUpdated(Order order);

    void createByFabric(DataAssetOrderFabricDto dataAssetOrderFabricDto);

    void updateByFabric(DataAssetOrderFabricDto dataAssetOrderFabricDto);

    boolean getAgreeOrderLock(Order order);

    boolean getApplyOrderLock(Asset asset);
}
