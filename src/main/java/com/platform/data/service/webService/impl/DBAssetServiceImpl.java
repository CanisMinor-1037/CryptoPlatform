package com.platform.data.service.webService.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.data.entity.fabricDto.DBAssetFabricDto;
import com.platform.data.entity.po.DBAsset;
import com.platform.data.entity.po.Department;
import com.platform.data.mapper.DBAssetMapper;
import com.platform.data.mapper.DepartmentMapper;
import com.platform.data.service.webService.DBAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DBAssetServiceImpl extends ServiceImpl<DBAssetMapper, DBAsset> implements DBAssetService {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Value("${department.name}")
    private String departmentName;
    @Override
    public void createByFabric(DBAssetFabricDto dbAssetFabricDto) {
        LambdaQueryWrapper<Department> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Department::getFabricId,dbAssetFabricDto.getOwnerId());
        String owner = departmentMapper.selectOne(lambdaQueryWrapper).getName();
        DBAsset dbAsset = new DBAsset();
        dbAsset.setFabricId(dbAssetFabricDto.getId());
        dbAsset.setOwner(owner);
        dbAsset.setName(dbAssetFabricDto.getName());
        dbAsset.setPolicy(dbAssetFabricDto.getPolicy());
        dbAsset.setLocation(dbAssetFabricDto.getLocation());
        dbAsset.setField(dbAssetFabricDto.getField());
        dbAsset.setUrl(dbAssetFabricDto.getJdbcUrl());
        dbAsset.setUsername(dbAssetFabricDto.getUsername());
        dbAsset.setPassword(dbAssetFabricDto.getPassword());
        dbAsset.setCreateTime(LocalDateTime.now());
        dbAsset.setAesKey(dbAssetFabricDto.getAesKey());
        dbAsset.setEncType(dbAssetFabricDto.getEncType());
        save(dbAsset);
    }

    @Override
    public List<DBAsset> databaseAvailable() {
        LambdaQueryWrapper<DBAsset> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.ne(DBAsset::getOwner,departmentName);
        return list(lambdaQueryWrapper);
    }

    @Override
    public List<DBAsset> ownerDatabase() {
        LambdaQueryWrapper<DBAsset> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(DBAsset::getOwner,departmentName);
        return list(lambdaQueryWrapper);
    }
}
