package com.platform.data.service.webService;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.data.entity.fabricDto.DBAssetOrderFabricDto;
import com.platform.data.entity.po.DBAssetOrder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface DBAssetOrderService extends IService<DBAssetOrder> {
    void createByFabric(DBAssetOrderFabricDto dbAssetOrderFabricDto);

    List<DBAssetOrder> databaseApplyHistory();

    List<DBAssetOrder> databaseAppliedHistory();

    List<Long> applyCountByEachMonth();

    List<Long> appliedCountByEachMonth();
}
