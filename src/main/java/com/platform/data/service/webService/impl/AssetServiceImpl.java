package com.platform.data.service.webService.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.data.entity.dto.NameAndValue;
import com.platform.data.entity.dto.Storage;
import com.platform.data.entity.fabricDto.DataAssetFabricDto;
import com.platform.data.entity.po.Department;
import com.platform.data.mapper.AssetMapper;
import com.platform.data.mapper.DepartmentMapper;
import com.platform.data.service.webService.AssetService;
import com.platform.data.entity.po.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AssetServiceImpl extends ServiceImpl<BaseMapper<Asset>,Asset> implements AssetService {
    @Autowired
    private AssetMapper assetMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Value("${department.name}")
    private String departmentName;
    @Override
    public ArrayList<NameAndValue> selectCountGroupByLocation() {
        return assetMapper.selectCountGroupByLocation();
    }
    @Override
    public ArrayList<NameAndValue> selectCountGroupByField(){
        return assetMapper.selectCountGroupByField();
    }

    @Override
    public ArrayList<Storage> selectStorageInfo(){
        return assetMapper.getStorageInfo();
    }
    @Override
    public List<Asset> fileAvailable(){
        LambdaQueryWrapper<Asset> lambdaQueryWrapper= new LambdaQueryWrapper<>();
        lambdaQueryWrapper.ne(Asset::getOwner, departmentName);
        return list(lambdaQueryWrapper);
    }
    @Override
    public List<Asset> ownerFile(){
        LambdaQueryWrapper<Asset> lambdaQueryWrapper= new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Asset::getOwner, departmentName);
        return list(lambdaQueryWrapper);
    }
    @Override
    public void createByFabric(DataAssetFabricDto dataAssetFabricDto){
        LambdaQueryWrapper<Department> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Department::getFabricId,dataAssetFabricDto.getOwnerId());
        String owner = departmentMapper.selectOne(lambdaQueryWrapper).getName();
        Asset asset = new Asset();
        asset.setFabricId(dataAssetFabricDto.getId());
        asset.setOwner(owner);
        asset.setName(dataAssetFabricDto.getName());
        asset.setPolicy(dataAssetFabricDto.getPolicy());
        asset.setLocation(dataAssetFabricDto.getLocation());
        asset.setField(dataAssetFabricDto.getField());
        asset.setUrl(dataAssetFabricDto.getCid());
        asset.setCreateTime(LocalDateTime.now());
        asset.setAesKey(dataAssetFabricDto.getAesKey());
        asset.setEncType(dataAssetFabricDto.getEncType());
        save(asset);
    }

}
