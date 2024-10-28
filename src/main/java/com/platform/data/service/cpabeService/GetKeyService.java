package com.platform.data.service.cpabeService;

import com.platform.data.entity.fabricDto.AttributeApplicationFabricDto;
import com.platform.data.entity.fabricDto.DepartmentFabricDto;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface GetKeyService {
    void updateByFabric(AttributeApplicationFabricDto attributeApplicationFabricDto) throws IOException, NoSuchAlgorithmException, ClassNotFoundException;
    void updateByFabric(DepartmentFabricDto departmentFabricDto) throws IOException, NoSuchAlgorithmException, ClassNotFoundException;
}
