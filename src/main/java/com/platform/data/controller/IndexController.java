package com.platform.data.controller;

import com.platform.data.common.Response;
import com.platform.data.entity.dto.*;
import com.platform.data.service.webService.AssetService;
import com.platform.data.service.webService.DepartmentService;
import com.platform.data.service.webService.OrderHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Api("首页")
@RestController
@CrossOrigin(origins = "*")
public class IndexController {
    private final String INDEX = "/index";
    private final String ORDER_INFO = "/orderInfo";
    private final String APPLICATION_INFO = "/applicationInfo";
    private final String TIMES_RANKING = "/timesRanking";
    private final String REAL_TIME = "/realTime";
    private final String DEPARTMENT_ORDER_COUNT = "/departmentOrderCount";
    private final String RECENT_DEPARTMENT_ORDER_EXISTS = "/recentDepartmentOrderExists";
    private final String DEPARTMENT_RANKING = "/departmentRanking";
    private final String STORAGE = "/storage";
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private OrderHistoryService orderHistoryService;
    @Autowired
    private AssetService assetService;

    @ApiOperation("获取数据要素流通统计信息")
    @GetMapping(INDEX + ORDER_INFO)
    public Response<OrderInfo> orderInfo(){
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setDepartment(departmentService.count());
        orderInfo.setTotal(orderHistoryService.count());
        orderInfo.setLastWeek(orderHistoryService.countByLastWeek());
        orderInfo.setThisYear(orderHistoryService.countByThisYear());
        orderInfo.setBarData(orderHistoryService.countByEachMonth());
        return Response.success(orderInfo);
    }
    @ApiOperation("获取数据要素资产分类信息")
    @GetMapping(INDEX + APPLICATION_INFO)
    public Response<ArrayList<ArrayList<NameAndValue>>> applicationInfo(){
        ArrayList<ArrayList<NameAndValue>> applicationInfo = new ArrayList<>();
        ArrayList<NameAndValue> position = assetService.selectCountGroupByLocation();
        ArrayList<NameAndValue> field = assetService.selectCountGroupByField();
        applicationInfo.add(position);
        applicationInfo.add(field);
        return Response.success(applicationInfo);
    }
    @ApiOperation("获取数据要素流通次数排行信息")
    @GetMapping(INDEX + TIMES_RANKING)
    public Response<ArrayList<NameAndValue>> timesRanking(){
        return Response.success(orderHistoryService.selectCountGroupByName());
    }
    @ApiOperation("获取数据要素实时流通信息")
    @GetMapping(INDEX + REAL_TIME)
    public Response<ArrayList<RealTime>> realTime(){
        return Response.success(orderHistoryService.selectRealTimeInfo());
    }
    @ApiOperation("获取机构排行信息")
    @GetMapping(INDEX + DEPARTMENT_RANKING)
    public Response<ArrayList<DepartmentRanking>> departmentRanking(){
        ArrayList<DepartmentRanking> departmentRanking = new ArrayList<>();
        DepartmentRanking apply = orderHistoryService.selectTopFiveApply();
        DepartmentRanking applied = orderHistoryService.selectTopFiveApplied();
        departmentRanking.add(apply);
        departmentRanking.add(applied);
        return Response.success(departmentRanking);
    }
    @ApiOperation("获取区块链数据要素可信存储信息")
    @GetMapping(INDEX + STORAGE)
    public Response<ArrayList<Storage>> storage(){
        return Response.success(assetService.selectStorageInfo());
    }
    @ApiOperation("获取机构数据要素流通总次数")
    @GetMapping(INDEX + DEPARTMENT_ORDER_COUNT)
    public Response<List<NameAndValue>> departmentOrderCount(){
        return Response.success(departmentService.departmentOrderCount());
    }
    @ApiOperation("获取近30分钟之内数据要素流通情况，是否有数据要素从一个部门流向另外一个部门")
    @GetMapping(INDEX + RECENT_DEPARTMENT_ORDER_EXISTS)
    public Response<List<OwnerAndApplicant>> recentDepartmentOrderExists(){
        return Response.success(departmentService.recentDepartmentOrderExists());
    }
}
