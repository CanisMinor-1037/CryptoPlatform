package com.platform.data.service.webService.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.platform.data.entity.fabricDto.AttributeApplicationFabricDto;
import com.platform.data.entity.po.AttributeHistory;
import com.platform.data.service.webService.AttributeHistoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AttributeHistoryServiceImpl extends ServiceImpl<BaseMapper<AttributeHistory>,AttributeHistory> implements AttributeHistoryService {
    @Override
    public void createByFabric(AttributeApplicationFabricDto attributeApplicationFabricDto){
        AttributeHistory attributeHistory = new AttributeHistory();
        attributeHistory.setFabricId(attributeApplicationFabricDto.getId());
        attributeHistory.setAttribute(attributeApplicationFabricDto.getAttribute());
        attributeHistory.setTime(LocalDateTime.now());
        save(attributeHistory);
    }
}
