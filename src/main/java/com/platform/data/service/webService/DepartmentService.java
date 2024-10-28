package com.platform.data.service.webService;

import com.baomidou.mybatisplus.extension.service.IService;
import com.platform.data.entity.dto.ApplyAttributes;
import com.platform.data.entity.dto.NameAndValue;
import com.platform.data.entity.dto.OwnerAndApplicant;
import com.platform.data.entity.fabricDto.AttributeApplicationFabricDto;
import com.platform.data.entity.fabricDto.DepartmentFabricDto;
import com.platform.data.entity.po.Department;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface DepartmentService extends IService<Department> {
    Department getMyDepartment();
    List<String> getMyAttributes();

    void createByFabric(DepartmentFabricDto departmentFabricDto);

    void updateByFabric(DepartmentFabricDto departmentFabricDto);

    void updateByFabric(AttributeApplicationFabricDto attributeApplicationFabricDto);

    boolean isUpdated(ApplyAttributes attributes);

    List<NameAndValue> departmentOrderCount();

    List<OwnerAndApplicant> recentDepartmentOrderExists();
}
