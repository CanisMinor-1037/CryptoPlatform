package com.platform.data.controller;

import com.platform.data.common.Response;
import com.platform.data.constant.AttributeApplicationStatusConstant;
import com.platform.data.entity.po.AttributeApplication;
import com.platform.data.entity.po.Department;
import com.platform.data.service.webService.AttributeApplicationService;
import com.platform.data.service.webService.DepartmentService;
import com.platform.data.service.fabricService.AttributeApplicationFabricService;
import com.platform.data.service.fabricService.AttributeItemFabricService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hyperledger.fabric.client.CommitException;
import org.hyperledger.fabric.client.GatewayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("属性授权")
@RestController
@CrossOrigin(origins = "*")
public class AttributeAuthorityController {
    private final String ATTRIBUTE_AUTHORITY = "/attributeAuthority";
    private final String ATTRIBUTE_LIST = "/attributeList";
    private final String DEPARTMENT_LIST = "/departmentList";
    private final String APPLICATION_UNPROCESSED = "/applicationUnprocessed";
    private final String APPLICATION_PROCESSED = "/applicationProcessed";
    private final String AGREE = "/agree";
    private final String REFUSE = "/refuse";
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private AttributeApplicationService attributeApplicationService;
    @Autowired
    private AttributeItemFabricService attributeFabricService;
    @Autowired
    private AttributeApplicationFabricService attributeApplicationFabricService;
    @ApiOperation("获取属性列表信息")
    @GetMapping(ATTRIBUTE_AUTHORITY + ATTRIBUTE_LIST)
    public Response<List<String>> attributeList() throws GatewayException {
        return Response.success(attributeFabricService.getAllAttributeItemString());
    }
    @ApiOperation("获取部门列表信息")
    @GetMapping(ATTRIBUTE_AUTHORITY + DEPARTMENT_LIST)
    public Response<List<Department>> departmentList(){
        return Response.success(departmentService.list());
    }
    @ApiOperation("获取已处理申请信息")
    @GetMapping(ATTRIBUTE_AUTHORITY + APPLICATION_PROCESSED)
    public Response<List<AttributeApplication>> processed(){
        return Response.success(attributeApplicationService.processed());
    }
    @ApiOperation("获取未处理申请信息")
    @GetMapping(ATTRIBUTE_AUTHORITY + APPLICATION_UNPROCESSED)
    public Response<List<AttributeApplication>> unprocessed(){
        return Response.success(attributeApplicationService.unprocessed());
    }
    @ApiOperation("同意属性申请")
    @PostMapping(ATTRIBUTE_AUTHORITY + AGREE)
    public Response<String> agree(@RequestBody AttributeApplication attributeApplication) throws CommitException, GatewayException {
        if(attributeApplicationService.isUpdated(attributeApplication)){
            return Response.fail("交易已经同步到数据库，请刷新页面");
        }
        if(!attributeApplicationService.getAgreeAttributeLock(attributeApplication)){
            return Response.fail("已经同意或拒绝，交易尚未被处理，请勿重新发送");
        }
        attributeApplicationFabricService.updateAttributeApplicationStatus(attributeApplication.getFabricId(), AttributeApplicationStatusConstant.AGREED);
        return Response.success("ok");
    }
    @ApiOperation("拒绝属性申请")
    @PostMapping(ATTRIBUTE_AUTHORITY + REFUSE)
    public Response<String> refuse(@RequestBody AttributeApplication attributeApplication) throws CommitException, GatewayException {
        if(attributeApplicationService.isUpdated(attributeApplication)){
            return Response.fail("交易已经同步到数据库，请刷新页面");
        }
        if(!attributeApplicationService.getAgreeAttributeLock(attributeApplication)){
            return Response.fail("已经同意或拒绝，交易尚未被处理，请勿重新发送");
        }
        attributeApplicationFabricService.updateAttributeApplicationStatus(attributeApplication.getFabricId(), AttributeApplicationStatusConstant.REFUSED);
        return Response.success("ok");
    }
}
