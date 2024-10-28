package com.platform.data.service.webService.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.data.constant.AttributeApplicationStatusConstant;
import com.platform.data.constant.RedisConstant;
import com.platform.data.entity.dto.ApplyAttributes;
import com.platform.data.entity.fabricDto.AttributeApplicationFabricDto;
import com.platform.data.entity.po.AttributeApplication;
import com.platform.data.entity.po.Department;
import com.platform.data.mapper.DepartmentMapper;
import com.platform.data.service.webService.AttributeApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttributeApplicationServiceImpl extends ServiceImpl<BaseMapper<AttributeApplication>,AttributeApplication> implements AttributeApplicationService {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Value("${department.name}")
    private String departmentName;
    public List<AttributeApplication> processed() {
        LambdaQueryWrapper<AttributeApplication> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.ne(AttributeApplication::getStatus,0);
        return list(lambdaQueryWrapper);
    }
    public List<AttributeApplication> unprocessed() {
        LambdaQueryWrapper<AttributeApplication> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(AttributeApplication::getStatus,0);
        return list(lambdaQueryWrapper);
    }
    @Override
    public boolean getApplyAttributeLock(ApplyAttributes attributes) {
        return redisTemplate.opsForValue().setIfAbsent(RedisConstant.ATTRIBUTION_APPLY_PREFIX,1);
    }
    @Override
    public boolean getAgreeAttributeLock(AttributeApplication attributeApplication){
        String fabricId = attributeApplication.getFabricId();
        return redisTemplate.opsForValue().setIfAbsent(RedisConstant.ATTRIBUTION_AGREE_PREFIX + fabricId,1);
    }
    @Override
    public boolean isUpdated(AttributeApplication attributeApplication){
        LambdaQueryWrapper<AttributeApplication> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(AttributeApplication::getId,attributeApplication.getId());
        AttributeApplication mysqlAttributeApplication = getOne(lambdaQueryWrapper);
        return mysqlAttributeApplication != null && mysqlAttributeApplication.getStatus() != AttributeApplicationStatusConstant.UNPROCESSED;
    }
    @Override
    public void createByFabric(AttributeApplicationFabricDto attributeApplicationFabricDto) {
        LambdaQueryWrapper<Department> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Department::getFabricId,attributeApplicationFabricDto.getDepartmentId());
        String applicant = departmentMapper.selectOne(lambdaQueryWrapper).getName();
        AttributeApplication attributeApplication = new AttributeApplication();
        attributeApplication.setFabricId(attributeApplicationFabricDto.getId());
        attributeApplication.setApplicant(applicant);
        attributeApplication.setAttribute(attributeApplicationFabricDto.getAttribute());
        attributeApplication.setStatus(AttributeApplicationStatusConstant.UNPROCESSED);
        attributeApplication.setTime(LocalDateTime.now());
        save(attributeApplication);
    }
    @Override
    public void updateByFabric(AttributeApplicationFabricDto attributeApplicationFabricDto) {
        LambdaQueryWrapper<Department> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Department::getFabricId,attributeApplicationFabricDto.getDepartmentId());
        String applicant = departmentMapper.selectOne(lambdaQueryWrapper).getName();
        LambdaUpdateWrapper<AttributeApplication> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(AttributeApplication::getFabricId,attributeApplicationFabricDto.getId())
                .set(AttributeApplication::getApplicant,applicant)
                .set(AttributeApplication::getAttribute,attributeApplicationFabricDto.getAttribute())
                .set(AttributeApplication::getStatus,attributeApplicationFabricDto.getStatus())
                .set(AttributeApplication::getTime, LocalDateTime.now());
        update(lambdaUpdateWrapper);
        if(applicant.equals(departmentName)){
            redisTemplate.delete(RedisConstant.ATTRIBUTION_APPLY_PREFIX);
        }
        redisTemplate.delete(RedisConstant.ATTRIBUTION_AGREE_PREFIX + attributeApplicationFabricDto.getId());
    }
}
