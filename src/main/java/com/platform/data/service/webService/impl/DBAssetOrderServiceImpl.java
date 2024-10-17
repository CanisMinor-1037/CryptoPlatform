package com.platform.data.service.webService.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.data.entity.fabricDto.DBAssetOrderFabricDto;
import com.platform.data.entity.po.*;
import com.platform.data.mapper.DBAssetMapper;
import com.platform.data.mapper.DBAssetOrderMapper;
import com.platform.data.mapper.DepartmentMapper;
import com.platform.data.service.webService.DBAssetOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DBAssetOrderServiceImpl extends ServiceImpl<DBAssetOrderMapper, DBAssetOrder> implements DBAssetOrderService {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private DBAssetMapper dbAssetMapper;
    @Value("${department.name}")
    private String departmentName;
    @Override
    public void createByFabric(DBAssetOrderFabricDto dbAssetOrderFabricDto) {
        LambdaQueryWrapper<Department> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Department::getFabricId,dbAssetOrderFabricDto.getApplicantId());
        String applicant = departmentMapper.selectOne(lambdaQueryWrapper).getName();
        LambdaQueryWrapper<DBAsset> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper1.eq(DBAsset::getFabricId,dbAssetOrderFabricDto.getDbAssetId());
        DBAsset dbAsset = dbAssetMapper.selectOne(lambdaQueryWrapper1);
        DBAssetOrder dbAssetOrder = new DBAssetOrder();
        dbAssetOrder.setFabricId(dbAssetOrderFabricDto.getId());
        dbAssetOrder.setApplicant(applicant);
        dbAssetOrder.setOwner(dbAsset.getOwner());
        dbAssetOrder.setName(dbAsset.getName());
        dbAssetOrder.setPolicy(dbAsset.getPolicy());
        dbAssetOrder.setCreateTime(LocalDateTime.now());
        dbAssetOrder.setSql(dbAssetOrderFabricDto.getSql());
        save(dbAssetOrder);
    }

    @Override
    public List<DBAssetOrder> databaseApplyHistory() {
        LambdaQueryWrapper<DBAssetOrder> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DBAssetOrder::getApplicant,departmentName);
        return list(lambdaQueryWrapper);
    }

    @Override
    public List<DBAssetOrder> databaseAppliedHistory() {
        LambdaQueryWrapper<DBAssetOrder> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DBAssetOrder::getOwner,departmentName);
        return list(lambdaQueryWrapper);
    }

    @Override
    public List<Long> applyCountByEachMonth() {
        int year = LocalDate.now().getYear();
        ArrayList<Long> result = new ArrayList<>();
        String format = "%d-%d-01 00:00:00";
        for(int month = 1;month < 12;month ++){
            LambdaQueryWrapper<DBAssetOrder> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.ge(DBAssetOrder::getCreateTime,String.format(format,year,month)).lt(DBAssetOrder::getCreateTime,String.format(format,year,month+1)).eq(DBAssetOrder::getApplicant,departmentName);
            result.add(count(lambdaQueryWrapper));
        }
        LambdaQueryWrapper<DBAssetOrder> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.ge(DBAssetOrder::getCreateTime,String.format(format,year,12)).lt(DBAssetOrder::getCreateTime,String.format(format,year+1,1)).eq(DBAssetOrder::getApplicant,departmentName);
        result.add(count(lambdaQueryWrapper));
        return result;
    }

    @Override
    public List<Long> appliedCountByEachMonth() {
        int year = LocalDate.now().getYear();
        ArrayList<Long> result = new ArrayList<>();
        String format = "%d-%d-01 00:00:00";
        for(int month = 1;month < 12;month ++){
            LambdaQueryWrapper<DBAssetOrder> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.ge(DBAssetOrder::getCreateTime,String.format(format,year,month)).lt(DBAssetOrder::getCreateTime,String.format(format,year,month+1)).eq(DBAssetOrder::getOwner,departmentName);
            result.add(count(lambdaQueryWrapper));
        }
        LambdaQueryWrapper<DBAssetOrder> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.ge(DBAssetOrder::getCreateTime,String.format(format,year,12)).lt(DBAssetOrder::getCreateTime,String.format(format,year+1,1)).eq(DBAssetOrder::getOwner,departmentName);
        result.add(count(lambdaQueryWrapper));
        return result;
    }
}
