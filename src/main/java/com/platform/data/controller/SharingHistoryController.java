package com.platform.data.controller;

import com.platform.data.common.Response;
import com.platform.data.entity.po.OrderHistory;
import com.platform.data.service.webService.OrderHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api("共享历史")
@RestController
@CrossOrigin(origins = "*")
public class SharingHistoryController {
    private final String SHARING_HISTORY = "/sharingHistory";
    private final String ALL_FILE = "/allFile";
    @Autowired
    private OrderHistoryService orderHistoryService;
    @ApiOperation("获取数据要素共享信息")
    @GetMapping(SHARING_HISTORY + ALL_FILE)
    public Response<List<OrderHistory>> allFile(){
        return Response.success(orderHistoryService.list());
    }
}
