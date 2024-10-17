package com.platform.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.data.entity.dto.NameAndValue;
import com.platform.data.entity.dto.Storage;
import com.platform.data.entity.po.Asset;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface AssetMapper extends BaseMapper<Asset> {
    ArrayList<NameAndValue> selectCountGroupByField();
    ArrayList<NameAndValue> selectCountGroupByLocation();

    ArrayList<Storage> getStorageInfo();
}
