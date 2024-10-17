package com.platform.data.service.webService.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.data.constant.OrderStatusConstant;
import com.platform.data.constant.RedisConstant;
import com.platform.data.entity.fabricDto.DataAssetOrderFabricDto;
import com.platform.data.entity.po.*;
import com.platform.data.mapper.AssetMapper;
import com.platform.data.mapper.DepartmentMapper;
import com.platform.data.service.webService.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl extends ServiceImpl<BaseMapper<Order>,Order> implements OrderService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private AssetMapper assetMapper;
    @Value("${department.name}")
    private String departmentName;
    @Override
    public List<Order> fileApplyHistory() {
        LambdaQueryWrapper<Order> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Order::getApplicant,departmentName);
        return list(lambdaQueryWrapper);
    }
    @Override
    public List<Order> fileAppliedHistory() {
        LambdaQueryWrapper<Order> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Order::getOwner,departmentName);
        return list(lambdaQueryWrapper);
    }
    @Override
    public boolean isUpdated(Order order){
        LambdaQueryWrapper<Order> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Order::getId,order.getId());
        Order mysqlOrder = getOne(lambdaQueryWrapper);
        return mysqlOrder != null && mysqlOrder.getStatus() != OrderStatusConstant.UNPROCESSED;
    }
    @Override
    public boolean getAgreeOrderLock(Order order){
        String fabricId = order.getFabricId();
        return redisTemplate.opsForValue().setIfAbsent(RedisConstant.ASSET_AGREE_PREFIX + fabricId, 1);
    }
    @Override
    public boolean getApplyOrderLock(Asset asset){
        String fabricId = asset.getFabricId();
        return redisTemplate.opsForValue().setIfAbsent(RedisConstant.ASSET_APPLY_PREFIX + fabricId, 1);
    }
    @Override
    public void createByFabric(DataAssetOrderFabricDto dataAssetOrderFabricDto) {
        LambdaQueryWrapper<Department> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Department::getFabricId,dataAssetOrderFabricDto.getApplicantId());
        String applicant = departmentMapper.selectOne(lambdaQueryWrapper).getName();
        LambdaQueryWrapper<Asset> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper1.eq(Asset::getFabricId,dataAssetOrderFabricDto.getDataAssetId());
        Asset asset = assetMapper.selectOne(lambdaQueryWrapper1);
        Order order = new Order();
        order.setFabricId(dataAssetOrderFabricDto.getId());
        order.setApplicant(applicant);
        order.setOwner(asset.getOwner());
        order.setName(asset.getName());
        order.setPolicy(asset.getPolicy());
        order.setStatus(dataAssetOrderFabricDto.getStatus());
        order.setCreateTime(LocalDateTime.now());
        save(order);
        redisTemplate.delete(RedisConstant.ASSET_APPLY_PREFIX + dataAssetOrderFabricDto.getId());
    }
    @Override
    public void updateByFabric(DataAssetOrderFabricDto dataAssetOrderFabricDto) {
        LambdaUpdateWrapper<Order> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(Order::getFabricId,dataAssetOrderFabricDto.getId())
                .set(Order::getStatus,dataAssetOrderFabricDto.getStatus());
        update(lambdaUpdateWrapper);
        redisTemplate.delete(RedisConstant.ASSET_AGREE_PREFIX + dataAssetOrderFabricDto.getId());
    }
}
