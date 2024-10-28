package com.platform.data.controller;

import com.platform.data.common.Response;
import com.platform.data.entity.po.DBAssetOrder;
import com.platform.data.service.webService.DBAssetOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class DBAssetOrderController {
    private final String ORDER = "/order";
    private final String DATABASE_APPLY_HISTORY = "/databaseApplyHistory";
    private final String DATABASE_APPLIED_HISTORY = "/databaseAppliedHistory";
    @Autowired
    private DBAssetOrderService dbAssetOrderService;
    @ApiOperation("获取数据库申请信息")
    @GetMapping(ORDER + DATABASE_APPLY_HISTORY)
    public Response<List<DBAssetOrder>> databaseApplyHistory() {
        return Response.success(dbAssetOrderService.databaseApplyHistory());
    }
    @ApiOperation("获取数据库被申请信息")
    @GetMapping(ORDER + DATABASE_APPLIED_HISTORY)
    public Response<List<DBAssetOrder>> databaseAppliedHistory() {
        return Response.success(dbAssetOrderService.databaseAppliedHistory());
    }
}
