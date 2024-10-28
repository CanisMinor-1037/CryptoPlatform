package com.platform.data.controller;

import com.platform.data.common.Response;
import com.platform.data.constant.EncTypeConstant;
import com.platform.data.constant.OrderStatusConstant;
import com.platform.data.entity.dto.Database;
import com.platform.data.entity.po.DBAsset;
import com.platform.data.entity.po.VideoAsset;
import com.platform.data.service.fabricService.VideoAssetFabricService;
import com.platform.data.service.fabricService.VideoAssetOrderFabricService;
import com.platform.data.service.webService.VideoAssetOrderService;
import com.platform.data.service.webService.VideoAssetService;
import com.platform.data.util.EncUtil;
import com.platform.data.util.FabricIdUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class VideoAssetConfirmationController {
    private final String CONFIRMATION = "/confirmation";
    private final String UPLOAD_VIDEO_ASSET = "/uploadVideoAsset";
    private final String DOWNLOAD_VIDEO_ASSET = "/downloadVideoAsset";
    private final String VIDEO_AVAILABLE = "/videoAvailable";
    private final String OWNER_VIDEO = "/ownerVideo";
    private final String VIDEO_APPLY_AND_APPLIED = "/videoApplyAndApplied";
    @Autowired
    private VideoAssetFabricService videoAssetFabricService;
    @Autowired
    private VideoAssetOrderFabricService videoAssetOrderFabricService;
    @Autowired
    private VideoAssetOrderService videoAssetOrderService;
    @Autowired
    private VideoAssetService videoAssetService;
    @ApiOperation("获取视频申请与被申请次数")
    @GetMapping(CONFIRMATION + VIDEO_APPLY_AND_APPLIED)
    public Response<List<List<Long>>> applyAndApplied(){
        List<List<Long>> result = new ArrayList<>();
        List<Long> apply = videoAssetOrderService.applyCountByEachMonth();
        List<Long> applied = videoAssetOrderService.appliedCountByEachMonth();
        result.add(apply);
        result.add(applied);
        return Response.success(result);
    }
    @ApiOperation("获取可申请视频信息")
    @PostMapping(CONFIRMATION + VIDEO_AVAILABLE)
    public Response<List<VideoAsset>> videoAvailable() {
        return Response.success(videoAssetService.videoAvailable());
    }
    @ApiOperation("获取自有视频信息")
    @PostMapping(CONFIRMATION + OWNER_VIDEO)
    public Response<List<VideoAsset>> ownerVideo() {
        return Response.success(videoAssetService.ownervideo());
    }
    @ApiOperation("上传视频")
    @PostMapping(CONFIRMATION + UPLOAD_VIDEO_ASSET)
    public Response<String> uploadVideoAsset(String name, String url, String attribute, String location, String field) throws Exception {
        byte[] aesKey = EncUtil.getAESKey();
        byte[] encryptedUrl = EncUtil.aesEncrypt(aesKey,url.getBytes());
        String encryptedUrlString = Base64.getEncoder().encodeToString(encryptedUrl);
        byte[] encryptedAesKey = EncUtil.cpabeEncrypt(attribute,aesKey);
        String encryptedAesKeyString = Base64.getEncoder().encodeToString(encryptedAesKey);
        Long start = System.currentTimeMillis();
        videoAssetFabricService.createVideoAsset(FabricIdUtil.generateVideoAssetFabricId(),name,FabricIdUtil.getMyFabricId(),attribute,location,field,encryptedUrlString,encryptedAesKeyString, EncTypeConstant.FULL_FILE);
        Long end = System.currentTimeMillis();
        return Response.success((end-start)+"ms");
    }
    @ApiOperation("访问视频")
    @PostMapping(CONFIRMATION + DOWNLOAD_VIDEO_ASSET)
    public Response<String> downloadVideoAsset(@RequestBody VideoAsset videoAsset) throws Exception {
        try{
            String encryptedAesKeyString = videoAsset.getAesKey();
            byte[] encryptedAesKey = Base64.getDecoder().decode(encryptedAesKeyString);
            byte[] decryptedAesKey = EncUtil.cpabeDecrypt(encryptedAesKey);
            String encryptedUrlString = videoAsset.getUrl();
            byte[] encryptedUrl = Base64.getDecoder().decode(encryptedUrlString);
            byte[] decryptedUrl = EncUtil.aesDecrypt(decryptedAesKey,encryptedUrl);
            String url = new String(decryptedUrl);
            videoAssetOrderFabricService.createVideoAssetOrder(FabricIdUtil.generateVideoAssetOrderFabricId(),videoAsset.getFabricId(),FabricIdUtil.getMyFabricId(), OrderStatusConstant.SUCCEEDED);
            return Response.success(url);
        }
        catch (Exception e){
            e.printStackTrace();
            videoAssetOrderFabricService.createVideoAssetOrder(FabricIdUtil.generateVideoAssetOrderFabricId(),videoAsset.getFabricId(),FabricIdUtil.getMyFabricId(), OrderStatusConstant.FAILED);
            return Response.fail(null);
        }
    }
}
