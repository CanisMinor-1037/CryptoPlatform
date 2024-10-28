package com.platform.data.service.webService;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.data.entity.fabricDto.AttributeApplicationFabricDto;
import com.platform.data.entity.po.AttributeHistory;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AttributeHistoryService extends IService<AttributeHistory> {
    void createByFabric(AttributeApplicationFabricDto attributeApplicationFabricDto);

}
