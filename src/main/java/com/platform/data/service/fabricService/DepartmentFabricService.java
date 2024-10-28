package com.platform.data.service.fabricService;

import com.platform.data.entity.fabricDto.DepartmentFabricDto;
import org.hyperledger.fabric.client.CommitException;
import org.hyperledger.fabric.client.GatewayException;

import java.util.List;

public interface DepartmentFabricService {
    void createDepartment(String id,String name,String attribute) throws GatewayException, CommitException;
    DepartmentFabricDto readDepartment(String id) throws GatewayException;
    void updateDepartmentAttribute(String id,String attribute) throws GatewayException, CommitException;
    List<DepartmentFabricDto> getAllDepartments() throws GatewayException;
}
