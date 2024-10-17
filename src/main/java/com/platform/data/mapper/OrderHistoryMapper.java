package com.platform.data.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.platform.data.entity.dto.NameAndValue;
import com.platform.data.entity.dto.RealTime;
import com.platform.data.entity.po.OrderHistory;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

@Mapper
public interface OrderHistoryMapper extends BaseMapper<OrderHistory> {
    ArrayList<NameAndValue> selectCountGroupByName();
    ArrayList<RealTime> selectRealTimeInfo();

    ArrayList<String> selectTopFiveApply();

    ArrayList<Long> selectTopFiveApplyCount();

    ArrayList<String> selectTopFiveApplied();

    ArrayList<Long> selectTopFiveAppliedCount();

    ArrayList<NameAndValue> selectNameAndCountGroupByFabricId();
}
