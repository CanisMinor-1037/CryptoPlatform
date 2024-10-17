package com.platform.data.controller;

import com.platform.data.common.Response;
import com.platform.data.entity.po.DBAssetOrder;
import com.platform.data.entity.po.VideoAssetOrder;
import com.platform.data.service.webService.VideoAssetOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class VideoAssetOrderController {
    private final String ORDER = "/order";
    private final String VIDEO_APPLY_HISTORY = "/videoApplyHistory";
    private final String VIDEO_APPLIED_HISTORY = "/videoAppliedHistory";
    @Autowired
    private VideoAssetOrderService videoAssetOrderService;
    @ApiOperation("获取摄像头申请信息")
    @GetMapping(ORDER + VIDEO_APPLY_HISTORY)
    public Response<List<VideoAssetOrder>> videoApplyHistory() {
        return Response.success(videoAssetOrderService.videoApplyHistory());
    }
    @ApiOperation("获取摄像头被申请信息")
    @GetMapping(ORDER + VIDEO_APPLIED_HISTORY)
    public Response<List<VideoAssetOrder>> videoAppliedHistory() {
        return Response.success(videoAssetOrderService.videoAppliedHistory());
    }
}
