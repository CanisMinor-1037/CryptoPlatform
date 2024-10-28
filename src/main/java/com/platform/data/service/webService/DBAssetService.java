package com.platform.data.service.webService;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.data.entity.fabricDto.DBAssetFabricDto;
import com.platform.data.entity.po.DBAsset;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface DBAssetService extends IService<DBAsset> {
    void createByFabric(DBAssetFabricDto dbAssetFabricDto);

    List<DBAsset> databaseAvailable();

    List<DBAsset> ownerDatabase();
}
