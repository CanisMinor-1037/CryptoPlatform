package com.platform.data.service.fabricService;

import com.platform.data.entity.fabricDto.AttributeItemFabricDto;
import org.hyperledger.fabric.client.*;

import java.util.List;

public interface AttributeItemFabricService {
    void createAttributeItem(String id, String content) throws EndorseException, CommitException, SubmitException, CommitStatusException;
    void deleteAttributeItem(String id) throws EndorseException, CommitException, SubmitException, CommitStatusException;
    List<AttributeItemFabricDto> getAllAttributeItem() throws GatewayException;
    List<String> getAllAttributeItemString() throws GatewayException;

    AttributeItemFabricDto readAttributeItem(String id) throws GatewayException;
}
