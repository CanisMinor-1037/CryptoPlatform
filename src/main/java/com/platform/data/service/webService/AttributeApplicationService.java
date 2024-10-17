package com.platform.data.service.webService;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.data.entity.dto.ApplyAttributes;
import com.platform.data.entity.fabricDto.AttributeApplicationFabricDto;
import com.platform.data.entity.po.AttributeApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface AttributeApplicationService extends IService<AttributeApplication> {
    List<AttributeApplication> processed();
    List<AttributeApplication> unprocessed();

    boolean isUpdated(AttributeApplication attributeApplication);

    void createByFabric(AttributeApplicationFabricDto attributeApplicationFabricDto);

    void updateByFabric(AttributeApplicationFabricDto attributeApplicationFabricDto);

    boolean getApplyAttributeLock(ApplyAttributes attributes);

    boolean getAgreeAttributeLock(AttributeApplication attributeApplication);
}
