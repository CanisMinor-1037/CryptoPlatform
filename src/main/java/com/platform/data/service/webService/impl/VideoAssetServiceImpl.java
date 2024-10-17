package com.platform.data.service.webService.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.data.entity.fabricDto.VideoAssetFabricDto;
import com.platform.data.entity.po.DBAsset;
import com.platform.data.entity.po.Department;
import com.platform.data.entity.po.VideoAsset;
import com.platform.data.mapper.DepartmentMapper;
import com.platform.data.mapper.VideoAssetMapper;
import com.platform.data.service.webService.VideoAssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VideoAssetServiceImpl extends ServiceImpl<VideoAssetMapper, VideoAsset> implements VideoAssetService {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Value("${department.name}")
    private String departmentName;
    @Override
    public void createByFabric(VideoAssetFabricDto videoAssetFabricDto) {
        LambdaQueryWrapper<Department> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Department::getFabricId,videoAssetFabricDto.getOwnerId());
        String owner = departmentMapper.selectOne(lambdaQueryWrapper).getName();
        VideoAsset videoAsset = new VideoAsset();
        videoAsset.setFabricId(videoAssetFabricDto.getId());
        videoAsset.setOwner(owner);
        videoAsset.setName(videoAssetFabricDto.getName());
        videoAsset.setPolicy(videoAssetFabricDto.getPolicy());
        videoAsset.setLocation(videoAssetFabricDto.getLocation());
        videoAsset.setField(videoAssetFabricDto.getField());
        videoAsset.setUrl(videoAssetFabricDto.getRtspUrl());
        videoAsset.setCreateTime(LocalDateTime.now());
        videoAsset.setAesKey(videoAssetFabricDto.getAesKey());
        videoAsset.setEncType(videoAssetFabricDto.getEncType());
        save(videoAsset);
    }

    @Override
    public List<VideoAsset> videoAvailable() {
        LambdaQueryWrapper<VideoAsset> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.ne(VideoAsset::getOwner,departmentName);
        return list(lambdaQueryWrapper);
    }

    @Override
    public List<VideoAsset> ownervideo() {
        LambdaQueryWrapper<VideoAsset> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(VideoAsset::getOwner,departmentName);
        return list(lambdaQueryWrapper);
    }
}
