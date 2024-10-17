package com.platform.data.service.webService;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.data.entity.dto.DepartmentRanking;
import com.platform.data.entity.dto.NameAndValue;
import com.platform.data.entity.dto.RealTime;
import com.platform.data.entity.fabricDto.DataAssetOrderFabricDto;
import com.platform.data.entity.po.OrderHistory;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Transactional
public interface OrderHistoryService extends IService<OrderHistory> {
    long countByLastWeek();

    long countByThisYear();

    ArrayList<Long> countByEachMonth();

    ArrayList<NameAndValue> selectCountGroupByName();

    ArrayList<RealTime> selectRealTimeInfo();

    ArrayList<Long> applyCountByEachMonth();

    ArrayList<Long> appliedCountByEachMonth();

    DepartmentRanking selectTopFiveApply();


    DepartmentRanking selectTopFiveApplied();


    void createByFabric(DataAssetOrderFabricDto dataAssetOrderFabricDto);

    ArrayList<NameAndValue> selectNameAndCountGroupByFabricId();
}
