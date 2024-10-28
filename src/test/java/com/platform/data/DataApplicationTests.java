package com.platform.data;

import com.platform.data.service.fabricService.AttributeItemFabricService;
import com.platform.data.service.fabricService.DepartmentFabricService;
import com.platform.data.service.webService.DepartmentService;
import org.hyperledger.fabric.client.CommitException;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.GatewayException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class DataApplicationTests {
    @Autowired
    private Contract contract;
    @Autowired
    private DepartmentFabricService departmentFabricService;
    @Autowired
    private AttributeItemFabricService attributeItemFabricService;
    @Test
    void contextLoads() throws CommitException, GatewayException {
        /*departmentFabricService.createDepartment("Department1","属性授权中心","department:center");
        departmentFabricService.createDepartment("Department2","公安局","department:policeStation");
        departmentFabricService.createDepartment("Department3","医院","department:hospital");
        System.out.println(departmentFabricService.getAllDepartments());
        System.exit(0);
        System.out.println(attributeItemFabricService.getAllAttributeItem());
        attributeItemFabricService.createAttributeItem("AttributeItem2","department:center");
        attributeItemFabricService.createAttributeItem("AttributeItem3","department:policeStation");
        attributeItemFabricService.createAttributeItem("AttributeItem4","department:hospital");
        attributeItemFabricService.createAttributeItem("AttributeItem5","asset:video001");
        attributeItemFabricService.createAttributeItem("AttributeItem6","asset:file100");
        System.out.println(attributeItemFabricService.getAllAttributeItem());*/
        attributeItemFabricService.createAttributeItem("AttributeItem8","数据:一");
    }
}
