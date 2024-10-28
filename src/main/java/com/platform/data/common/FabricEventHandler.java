package com.platform.data.common;

import com.google.gson.Gson;
import com.platform.data.constant.ContractConstant;
import com.platform.data.entity.fabricDto.*;
import com.platform.data.service.cpabeService.GetKeyService;
import com.platform.data.service.webService.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@Component
public class FabricEventHandler {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private AssetService assetService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderHistoryService orderHistoryService;
    @Autowired
    private AttributeApplicationService attributeApplicationService;
    @Autowired
    private GetKeyService getKeyService;
    @Autowired
    private AttributeHistoryService attributeHistoryService;
    @Autowired
    private DBAssetService dbAssetService;
    @Autowired
    private DBAssetOrderService dbAssetOrderService;
    @Autowired
    private VideoAssetService videoAssetService;
    @Autowired
    private VideoAssetOrderService videoAssetOrderService;
    public void handleFabricEvent(String event,String payload) throws IOException, NoSuchAlgorithmException, ClassNotFoundException {
        if(event.equals(ContractConstant.CREATE_DEPARTMENT)){
            DepartmentFabricDto departmentFabricDto = new Gson().fromJson(payload, DepartmentFabricDto.class);
            departmentService.createByFabric(departmentFabricDto);
        }
        else if(event.equals(ContractConstant.UPDATE_DEPARTMENT_ATTRIBUTE)){
            DepartmentFabricDto departmentFabricDto = new Gson().fromJson(payload, DepartmentFabricDto.class);
            departmentService.updateByFabric(departmentFabricDto);
            getKeyService.updateByFabric(departmentFabricDto);
        }
        else if(event.equals(ContractConstant.CREATE_DATA_ASSET)){
            DataAssetFabricDto dataAssetFabricDto = new Gson().fromJson(payload, DataAssetFabricDto.class);
            assetService.createByFabric(dataAssetFabricDto);
        }
        else if(event.equals(ContractConstant.CREATE_DATA_ASSET_ORDER)){
            DataAssetOrderFabricDto dataAssetOrderFabricDto = new Gson().fromJson(payload, DataAssetOrderFabricDto.class);
            orderService.createByFabric(dataAssetOrderFabricDto);
            orderHistoryService.createByFabric(dataAssetOrderFabricDto);
        }
        else if(event.equals(ContractConstant.UPDATE_DATA_ASSET_ORDER_STATUS)){
            DataAssetOrderFabricDto dataAssetOrderFabricDto = new Gson().fromJson(payload, DataAssetOrderFabricDto.class);
            orderService.updateByFabric(dataAssetOrderFabricDto);
            orderHistoryService.createByFabric(dataAssetOrderFabricDto);
        }
        else if(event.equals(ContractConstant.CREATE_ATTRIBUTE_APPLICATION)){
            AttributeApplicationFabricDto attributeApplicationFabricDto = new Gson().fromJson(payload, AttributeApplicationFabricDto.class);
            attributeApplicationService.createByFabric(attributeApplicationFabricDto);
            attributeHistoryService.createByFabric(attributeApplicationFabricDto);
        }
        else if(event.equals(ContractConstant.UPDATE_ATTRIBUTE_APPLICATION_STATUS)){
            AttributeApplicationFabricDto attributeApplicationFabricDto = new Gson().fromJson(payload, AttributeApplicationFabricDto.class);
            attributeApplicationService.updateByFabric(attributeApplicationFabricDto);
            departmentService.updateByFabric(attributeApplicationFabricDto);
            getKeyService.updateByFabric(attributeApplicationFabricDto);
        }
        else if(event.equals(ContractConstant.CREATE_DB_ASSET)){
            DBAssetFabricDto dbAssetFabricDto = new Gson().fromJson(payload, DBAssetFabricDto.class);
            dbAssetService.createByFabric(dbAssetFabricDto);
        }
        else if(event.equals(ContractConstant.CREATE_DB_ASSET_ORDER)){
            DBAssetOrderFabricDto dbAssetOrderFabricDto =new Gson().fromJson(payload, DBAssetOrderFabricDto.class);
            dbAssetOrderService.createByFabric(dbAssetOrderFabricDto);
        }
        else if(event.equals(ContractConstant.CREATE_VIDEO_ASSET)){
            VideoAssetFabricDto videoAssetFabricDto = new Gson().fromJson(payload, VideoAssetFabricDto.class);
            videoAssetService.createByFabric(videoAssetFabricDto);
        }
        else if(event.equals(ContractConstant.CREATE_VIDEO_ASSET_ORDER)){
            VideoAssetOrderFabricDto videoAssetOrderFabricDto = new Gson().fromJson(payload, VideoAssetOrderFabricDto.class);
            videoAssetOrderService.createByFabric(videoAssetOrderFabricDto);
        }
        else if(event.equals(ContractConstant.UPDATE_VIDEO_ASSET_ORDER_STATUS2)){
            VideoAssetOrderFabricDto videoAssetOrderFabricDto = new Gson().fromJson(payload, VideoAssetOrderFabricDto.class);
            videoAssetOrderService.updateByFabric(videoAssetOrderFabricDto);
        }
        else{
            throw new RuntimeException("Unsupported event");
        }
    }
}
