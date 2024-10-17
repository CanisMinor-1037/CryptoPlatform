package com.platform.data.service.fabricService;

import com.platform.data.entity.fabricDto.AttributeApplicationFabricDto;
import org.hyperledger.fabric.client.CommitException;
import org.hyperledger.fabric.client.GatewayException;

import java.util.List;

public interface AttributeApplicationFabricService {
    void createAttributeApplication(String id,String departmentId,String attribute,int status) throws GatewayException, CommitException;
    AttributeApplicationFabricDto readAttributeApplication(String id) throws GatewayException;
    void updateAttributeApplicationStatus(String id,int status) throws GatewayException, CommitException;
    List<AttributeApplicationFabricDto> getAllAttributeApplication() throws GatewayException;
}
