package com.platform.data.service.webService.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.data.entity.dto.DepartmentRanking;
import com.platform.data.entity.dto.NameAndValue;
import com.platform.data.entity.dto.RealTime;
import com.platform.data.entity.fabricDto.DataAssetOrderFabricDto;
import com.platform.data.entity.po.Asset;
import com.platform.data.entity.po.Department;
import com.platform.data.mapper.AssetMapper;
import com.platform.data.mapper.DepartmentMapper;
import com.platform.data.mapper.OrderHistoryMapper;
import com.platform.data.entity.po.OrderHistory;
import com.platform.data.service.webService.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class OrderHistoryServiceImpl extends ServiceImpl<BaseMapper<OrderHistory>,OrderHistory> implements OrderHistoryService {
    @Autowired
    private OrderHistoryMapper orderHistoryMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private AssetMapper assetMapper;
    @Value("${department.name}")
    private String departmentName;
    @Override
    public long countByLastWeek(){
        return 0;
    }
    @Override
    public long countByThisYear(){
        int year = LocalDate.now().getYear();
        String startTime = year + "-01-01 00:00:00";
        String endTime = year + "-12-31 23:59:59";
        LambdaQueryWrapper<OrderHistory> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.ge(OrderHistory::getTime,startTime).lt(OrderHistory::getTime,endTime);
        return count(lambdaQueryWrapper);
    }
    @Override
    public ArrayList<Long> countByEachMonth(){
        int year = LocalDate.now().getYear();
        ArrayList<Long> result = new ArrayList<>();
        String format = "%d-%d-01 00:00:00";
        for(int month = 1;month < 12;month ++){
            LambdaQueryWrapper<OrderHistory> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.ge(OrderHistory::getTime,String.format(format,year,month)).lt(OrderHistory::getTime,String.format(format,year,month+1));
            result.add(count(lambdaQueryWrapper));
        }
        LambdaQueryWrapper<OrderHistory> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.ge(OrderHistory::getTime,String.format(format,year,12)).lt(OrderHistory::getTime,String.format(format,year+1,1));
        result.add(count(lambdaQueryWrapper));
        return result;
    }
    @Override
    public ArrayList<NameAndValue> selectCountGroupByName() {
        return orderHistoryMapper.selectCountGroupByName();
    }
    @Override
    public ArrayList<RealTime> selectRealTimeInfo() {
        return orderHistoryMapper.selectRealTimeInfo();
    }
    @Override
    public ArrayList<Long> applyCountByEachMonth(){
        int year = LocalDate.now().getYear();
        ArrayList<Long> result = new ArrayList<>();
        String format = "%d-%d-01 00:00:00";
        for(int month = 1;month < 12;month ++){
            LambdaQueryWrapper<OrderHistory> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.ge(OrderHistory::getTime,String.format(format,year,month)).lt(OrderHistory::getTime,String.format(format,year,month+1)).eq(OrderHistory::getApplicant,departmentName);
            result.add(count(lambdaQueryWrapper));
        }
        LambdaQueryWrapper<OrderHistory> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.ge(OrderHistory::getTime,String.format(format,year,12)).lt(OrderHistory::getTime,String.format(format,year+1,1)).eq(OrderHistory::getApplicant,departmentName);
        result.add(count(lambdaQueryWrapper));
        return result;
    }
    @Override
    public ArrayList<Long> appliedCountByEachMonth(){
        int year = LocalDate.now().getYear();
        ArrayList<Long> result = new ArrayList<>();
        String format = "%d-%d-01 00:00:00";
        for(int month = 1;month < 12;month ++){
            LambdaQueryWrapper<OrderHistory> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.ge(OrderHistory::getTime,String.format(format,year,month)).lt(OrderHistory::getTime,String.format(format,year,month+1)).eq(OrderHistory::getOwner,departmentName);
            result.add(count(lambdaQueryWrapper));
        }
        LambdaQueryWrapper<OrderHistory> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.ge(OrderHistory::getTime,String.format(format,year,12)).lt(OrderHistory::getTime,String.format(format,year+1,1)).eq(OrderHistory::getOwner,departmentName);
        result.add(count(lambdaQueryWrapper));
        return result;
    }
    @Override
    public DepartmentRanking selectTopFiveApply() {
        DepartmentRanking departmentRanking = new DepartmentRanking();
        departmentRanking.setDepartment(orderHistoryMapper.selectTopFiveApply());
        departmentRanking.setTimes(orderHistoryMapper.selectTopFiveApplyCount());
        return departmentRanking;
    }
    @Override
    public DepartmentRanking selectTopFiveApplied() {
        DepartmentRanking departmentRanking = new DepartmentRanking();
        departmentRanking.setDepartment(orderHistoryMapper.selectTopFiveApplied());
        departmentRanking.setTimes(orderHistoryMapper.selectTopFiveAppliedCount());
        return departmentRanking;
    }
    @Override
    public void createByFabric(DataAssetOrderFabricDto dataAssetOrderFabricDto){
        LambdaQueryWrapper<Department> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Department::getFabricId,dataAssetOrderFabricDto.getApplicantId());
        String applicant = departmentMapper.selectOne(lambdaQueryWrapper).getName();
        LambdaQueryWrapper<Asset> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper1.eq(Asset::getFabricId,dataAssetOrderFabricDto.getDataAssetId());
        Asset asset = assetMapper.selectOne(lambdaQueryWrapper1);
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setFabricId(dataAssetOrderFabricDto.getId());
        orderHistory.setApplicant(applicant);
        orderHistory.setOwner(asset.getOwner());
        orderHistory.setName(asset.getName());
        orderHistory.setPolicy(asset.getPolicy());
        orderHistory.setStatus(dataAssetOrderFabricDto.getStatus());
        orderHistory.setTime(LocalDateTime.now());
        save(orderHistory);
    }
    @Override
    public ArrayList<NameAndValue> selectNameAndCountGroupByFabricId(){
        return orderHistoryMapper.selectNameAndCountGroupByFabricId();
    }
}
