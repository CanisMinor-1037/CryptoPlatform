package com.platform.data.controller;

import com.platform.data.common.Response;
import com.platform.data.constant.OrderStatusConstant;
import com.platform.data.entity.po.Order;
import com.platform.data.service.webService.OrderService;
import com.platform.data.service.fabricService.DataAssetOrderFabricService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hyperledger.fabric.client.CommitException;
import org.hyperledger.fabric.client.GatewayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("订单")
@RestController
@CrossOrigin(origins = "*")
public class OrderController {
    private final String ORDER = "/order";
    private final String FILE_APPLY_HISTORY = "/fileApplyHistory";
    private final String FILE_APPLIED_HISTORY = "/fileAppliedHistory";
    private final String AGREE = "/agree";
    private final String REFUSE = "/refuse";
    @Autowired
    private OrderService orderService;
    @Autowired
    private DataAssetOrderFabricService dataAssetOrderFabricService;
    @ApiOperation("获取申请信息")
    @GetMapping(ORDER + FILE_APPLY_HISTORY)
    public Response<List<Order>> fileApplyHistory(){
        return Response.success(orderService.fileApplyHistory());
    }
    @ApiOperation("获取被申请信息")
    @GetMapping(ORDER + FILE_APPLIED_HISTORY)
    public Response<List<Order>> fileAppliedHistory(){
        return Response.success(orderService.fileAppliedHistory());
    }
    @ApiOperation("同意申请")
    @PostMapping(ORDER + AGREE)
    public Response<String> agree(@RequestBody Order order) throws CommitException, GatewayException {
        if(orderService.isUpdated(order)){
            return Response.fail("交易已经同步到数据库，请刷新页面");
        }
        if(!orderService.getAgreeOrderLock(order)){
            return Response.fail("已经同意或拒绝，交易尚未被处理，请勿重新发送");
        }
        dataAssetOrderFabricService.updateDataAssetOrderStatus(order.getFabricId(), OrderStatusConstant.TRANSPORTING);
        return Response.success("ok");
    }
    @ApiOperation("拒绝申请")
    @PostMapping(ORDER + REFUSE)
    public Response<String> refuse(@RequestBody Order order) throws CommitException, GatewayException {
        if(orderService.isUpdated(order)){
            return Response.fail("交易已经同步到数据库，请刷新页面");
        }
        if(!orderService.getAgreeOrderLock(order)){
            return Response.fail("已经同意或拒绝，交易尚未被处理，请勿重新发送");
        }
        dataAssetOrderFabricService.updateDataAssetOrderStatus(order.getFabricId(), OrderStatusConstant.REFUSED);
        return Response.success("ok");
    }


}
