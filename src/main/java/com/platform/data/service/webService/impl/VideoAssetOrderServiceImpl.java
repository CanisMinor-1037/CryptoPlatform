package com.platform.data.service.webService.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.data.entity.fabricDto.VideoAssetOrderFabricDto;
import com.platform.data.entity.po.DBAssetOrder;
import com.platform.data.entity.po.Department;
import com.platform.data.entity.po.VideoAsset;
import com.platform.data.entity.po.VideoAssetOrder;
import com.platform.data.mapper.DepartmentMapper;
import com.platform.data.mapper.VideoAssetMapper;
import com.platform.data.mapper.VideoAssetOrderMapper;
import com.platform.data.service.webService.VideoAssetOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VideoAssetOrderServiceImpl extends ServiceImpl<VideoAssetOrderMapper, VideoAssetOrder> implements VideoAssetOrderService {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private VideoAssetMapper videoAssetMapper;
    @Value("${department.name}")
    private String departmentName;
    @Override
    public void createByFabric(VideoAssetOrderFabricDto videoAssetOrderFabricDto) {
        LambdaQueryWrapper<Department> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Department::getFabricId,videoAssetOrderFabricDto.getApplicantId());
        String applicant = departmentMapper.selectOne(lambdaQueryWrapper).getName();
        LambdaQueryWrapper<VideoAsset> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper1.eq(VideoAsset::getFabricId,videoAssetOrderFabricDto.getVideoAssetId());
        VideoAsset videoAsset = videoAssetMapper.selectOne(lambdaQueryWrapper1);
        VideoAssetOrder videoAssetOrder = new VideoAssetOrder();
        videoAssetOrder.setFabricId(videoAsset.getFabricId());
        videoAssetOrder.setApplicant(applicant);
        videoAssetOrder.setOwner(videoAsset.getOwner());
        videoAssetOrder.setName(videoAsset.getName());
        videoAssetOrder.setPolicy(videoAsset.getPolicy());
        videoAssetOrder.setStatus(videoAssetOrderFabricDto.getStatus());
        videoAssetOrder.setCreateTime(LocalDateTime.now());
        save(videoAssetOrder);
    }

    @Override
    public void updateByFabric(VideoAssetOrderFabricDto videoAssetOrderFabricDto) {

    }

    @Override
    public List<VideoAssetOrder> videoApplyHistory() {
        LambdaQueryWrapper<VideoAssetOrder> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(VideoAssetOrder::getApplicant,departmentName);
        return list(lambdaQueryWrapper);
    }

    @Override
    public List<VideoAssetOrder> videoAppliedHistory() {
        LambdaQueryWrapper<VideoAssetOrder> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(VideoAssetOrder::getOwner,departmentName);
        return list(lambdaQueryWrapper);
    }

    @Override
    public List<Long> applyCountByEachMonth() {
        int year = LocalDate.now().getYear();
        ArrayList<Long> result = new ArrayList<>();
        String format = "%d-%d-01 00:00:00";
        for(int month = 1;month < 12;month ++){
            LambdaQueryWrapper<VideoAssetOrder> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.ge(VideoAssetOrder::getCreateTime,String.format(format,year,month)).lt(VideoAssetOrder::getCreateTime,String.format(format,year,month+1)).eq(VideoAssetOrder::getApplicant,departmentName);
            result.add(count(lambdaQueryWrapper));
        }
        LambdaQueryWrapper<VideoAssetOrder> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.ge(VideoAssetOrder::getCreateTime,String.format(format,year,12)).lt(VideoAssetOrder::getCreateTime,String.format(format,year+1,1)).eq(VideoAssetOrder::getApplicant,departmentName);
        result.add(count(lambdaQueryWrapper));
        return result;
    }

    @Override
    public List<Long> appliedCountByEachMonth() {
        int year = LocalDate.now().getYear();
        ArrayList<Long> result = new ArrayList<>();
        String format = "%d-%d-01 00:00:00";
        for(int month = 1;month < 12;month ++){
            LambdaQueryWrapper<VideoAssetOrder> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.ge(VideoAssetOrder::getCreateTime,String.format(format,year,month)).lt(VideoAssetOrder::getCreateTime,String.format(format,year,month+1)).eq(VideoAssetOrder::getOwner,departmentName);
            result.add(count(lambdaQueryWrapper));
        }
        LambdaQueryWrapper<VideoAssetOrder> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.ge(VideoAssetOrder::getCreateTime,String.format(format,year,12)).lt(VideoAssetOrder::getCreateTime,String.format(format,year+1,1)).eq(VideoAssetOrder::getOwner,departmentName);
        result.add(count(lambdaQueryWrapper));
        return result;
    }
}
