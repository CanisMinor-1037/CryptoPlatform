package com.platform.data.service.webService;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.data.entity.dto.NameAndValue;
import com.platform.data.entity.dto.Storage;
import com.platform.data.entity.fabricDto.DataAssetFabricDto;
import com.platform.data.entity.po.Asset;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
public interface AssetService extends IService<Asset> {
    ArrayList<NameAndValue> selectCountGroupByLocation();

    ArrayList<NameAndValue> selectCountGroupByField();

    ArrayList<Storage> selectStorageInfo();

    List<Asset> fileAvailable();

    List<Asset> ownerFile();

    void createByFabric(DataAssetFabricDto dataAssetFabricDto);

}
