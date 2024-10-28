package com.platform.data.service.webService.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.data.constant.AttributeApplicationStatusConstant;
import com.platform.data.constant.OrderStatusConstant;
import com.platform.data.entity.dto.ApplyAttributes;
import com.platform.data.entity.dto.NameAndValue;
import com.platform.data.entity.dto.OwnerAndApplicant;
import com.platform.data.entity.fabricDto.AttributeApplicationFabricDto;
import com.platform.data.entity.fabricDto.DepartmentFabricDto;
import com.platform.data.entity.po.Department;
import com.platform.data.entity.po.Order;
import com.platform.data.mapper.OrderMapper;
import com.platform.data.service.webService.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl extends ServiceImpl<BaseMapper<Department>,Department> implements DepartmentService {
    @Value("${department.name}")
    private String departmentName;
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public Department getMyDepartment() {
        LambdaQueryWrapper<Department> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Department::getName,departmentName);
        return getOne(lambdaQueryWrapper);
    }
    @Override
    public List<String> getMyAttributes(){
        LambdaQueryWrapper<Department> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Department::getName,departmentName).select(Department::getAttribute);
        String attribute = getOne(lambdaQueryWrapper).getAttribute();
        return Arrays.asList(attribute.split(" "));
    }
    @Override
    public void createByFabric(DepartmentFabricDto departmentFabricDto) {
        Department department = new Department();
        department.setFabricId(departmentFabricDto.getId());
        department.setName(departmentFabricDto.getName());
        department.setAttribute(departmentFabricDto.getAttribute());
        save(department);
    }
    @Override
    public void updateByFabric(DepartmentFabricDto departmentFabricDto){
        LambdaUpdateWrapper<Department> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(Department::getFabricId,departmentFabricDto.getId())
                .set(Department::getName,departmentFabricDto.getName())
                .set(Department::getAttribute,departmentFabricDto.getAttribute());
        update(lambdaUpdateWrapper);
    }
    @Override
    public void updateByFabric(AttributeApplicationFabricDto attributeApplicationFabricDto){
        if(attributeApplicationFabricDto.getStatus()!= AttributeApplicationStatusConstant.AGREED) {
            return;
        }
        LambdaUpdateWrapper<Department> lambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.eq(Department::getFabricId,attributeApplicationFabricDto.getDepartmentId())
                .set(Department::getAttribute,attributeApplicationFabricDto.getAttribute());
        update(lambdaUpdateWrapper);
    }
    @Override
    public boolean isUpdated(ApplyAttributes attributes){
        LambdaQueryWrapper<Department> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Department::getName,departmentName).select(Department::getAttribute);
        String attribute = getOne(lambdaQueryWrapper).getAttribute();
        return attribute != null && attribute.equals(attributes.getAttributes());
    }
    @Override
    public List<NameAndValue> departmentOrderCount(){
        List<String> names = list().stream().map(item->item.getName()).collect(Collectors.toList());
        List<NameAndValue> result = new ArrayList<>();
        names.forEach(name->{
            LambdaQueryWrapper<Order> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Order::getOwner,name).or().eq(Order::getApplicant,name);
            NameAndValue nameAndValue = new NameAndValue();
            nameAndValue.setName(name);
            nameAndValue.setValue(orderMapper.selectCount(lambdaQueryWrapper));
            result.add(nameAndValue);
        });
        return result;
    }
    @Override
    public List<OwnerAndApplicant> recentDepartmentOrderExists(){
        LocalDateTime endTime = LocalDateTime.now();
        LocalDateTime startTime = LocalDateTime.now().minusMinutes(30);
        List<String> names = list().stream().map(item->item.getName()).collect(Collectors.toList());
        List<OwnerAndApplicant> result = new ArrayList<>();
        names.forEach(name->{
            LambdaQueryWrapper<Order> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Order::getOwner,name).between(Order::getCreateTime,startTime,endTime).eq(Order::getStatus, OrderStatusConstant.SUCCEEDED);
            orderMapper.selectList(lambdaQueryWrapper).stream().map(item->item.getApplicant()).distinct().filter(applicantName->!name.equals(applicantName)).forEach(applicantName->{
                OwnerAndApplicant ownerAndApplicant = new OwnerAndApplicant();
                ownerAndApplicant.setOwner(name);
                ownerAndApplicant.setApplicant(applicantName);
                result.add(ownerAndApplicant);
            });
        });
        return result;
    }
}
