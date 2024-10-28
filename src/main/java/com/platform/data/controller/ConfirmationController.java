package com.platform.data.controller;

import com.platform.data.common.Response;
import com.platform.data.constant.AttributeApplicationStatusConstant;
import com.platform.data.constant.EncTypeConstant;
import com.platform.data.constant.OrderStatusConstant;
import com.platform.data.constant.UploadConstant;
import com.platform.data.entity.dto.ApplyAttributes;
import com.platform.data.entity.po.Asset;
import com.platform.data.service.fabricService.*;
import com.platform.data.service.ipfsService.FileOperationService;
import com.platform.data.service.webService.*;
import com.platform.data.util.EncUtil;
import com.platform.data.util.FabricIdUtil;
import com.platform.data.util.VideoUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hyperledger.fabric.client.CommitException;
import org.hyperledger.fabric.client.GatewayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Api("确权申请")
@RestController
@CrossOrigin(origins = "*")
public class ConfirmationController {
    private final String CONFIRMATION = "/confirmation";
    private final String APPLY_AND_APPLIED = "/applyAndApplied";
    private final String GET_ALL_ATTRIBUTES = "/getAllAttributes";
    private final String FILE_AVAILABLE = "/fileAvailable";
    private final String OWNER_FILE = "/ownerFile";
    private final String APPLY_ATTRIBUTES = "/applyAttributes";
    private final String APPLY_FILE = "/applyFile";
    private final String UPLOAD_FILE = "/uploadFile";
    private final String DOWNLOAD_FILE = "/downloadFile";
    private final String UPLOAD_SHARDED_FILE = "/uploadShardedFile";
    private final String DOWNLOAD_SHARDED_FILE = "/downloadShardedFile";
    @Autowired
    private AssetService assetService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private OrderHistoryService orderHistoryService;
    @Autowired
    private AttributeApplicationService attributeApplicationService;
    @Autowired
    private AttributeItemFabricService attributeFabricService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private DataAssetOrderFabricService dataAssetOrderFabricService;
    @Autowired
    private FileOperationService fileOperationService;
    @Autowired
    private DataAssetFabricService dataAssetFabricService;
    @Autowired
    private AttributeApplicationFabricService attributeApplicationFabricService;
    @Autowired
    private DBAssetFabricService dbAssetFabricService;
    @Autowired
    private VideoAssetFabricService videoAssetFabricService;
    @ApiOperation("获取所有可申请的属性和已拥有的属性")
    @GetMapping(CONFIRMATION + GET_ALL_ATTRIBUTES)
    public Response<List<List<String>>> getAllAttributes() throws GatewayException {
        List<List<String>> result = new ArrayList<>();
        result.add(attributeFabricService.getAllAttributeItemString());
        result.add(departmentService.getMyAttributes());
        return Response.success(result);
    }
    @ApiOperation("获取普通文件申请与被申请次数")
    @GetMapping(CONFIRMATION + APPLY_AND_APPLIED)
    public Response<List<List<Long>>> applyAndApplied(){
        List<List<Long>> result = new ArrayList<>();
        List<Long> apply = orderHistoryService.applyCountByEachMonth();
        List<Long> applied =orderHistoryService.appliedCountByEachMonth();
        result.add(apply);
        result.add(applied);
        return Response.success(result);
    }
    @ApiOperation("获取可申请普通文件信息")
    @GetMapping(CONFIRMATION + FILE_AVAILABLE)
    public Response<List<Asset>> fileAvailable(){
        return Response.success(assetService.fileAvailable());
    }
    @ApiOperation("获取自有普通文件信息")
    @GetMapping(CONFIRMATION + OWNER_FILE)
    public Response<List<Asset>> ownerFile(){
        return Response.success(assetService.ownerFile());
    }
    @ApiOperation("申请属性")
    @PostMapping(CONFIRMATION + APPLY_ATTRIBUTES)
    public Response<String> applyAttributes(@RequestBody ApplyAttributes attributes) throws CommitException, GatewayException {
        if(departmentService.isUpdated(attributes)){
            return Response.fail("申请属性不能与当前属性相同，若已申请，请刷新页面");
        }
        if(!attributeApplicationService.getApplyAttributeLock(attributes)){
            return Response.fail("已经发送过属性申请请求，尚未被处理，请勿重新发送请求");
        }
        attributeApplicationFabricService.createAttributeApplication(FabricIdUtil.generateAttributeApplicationFabricId(), FabricIdUtil.getMyFabricId(), attributes.getAttributes(), AttributeApplicationStatusConstant.UNPROCESSED);
        return Response.success("ok");
    }
    @ApiOperation("申请普通文件")
    @PostMapping(CONFIRMATION + APPLY_FILE)
    public Response<String> applyFile(@RequestBody Asset asset) throws CommitException, GatewayException {
        if(!orderService.getApplyOrderLock(asset)){
            return Response.fail("已经发送过文件申请请求，尚未被处理，请勿重新发送请求");
        }
        dataAssetOrderFabricService.createDataAssetOrder(FabricIdUtil.generateOrderFabricId(),asset.getFabricId(),FabricIdUtil.getMyFabricId(), OrderStatusConstant.UNPROCESSED);
        return Response.success("ok");
    }
    @ApiOperation("上传普通文件")
    @PostMapping(CONFIRMATION + UPLOAD_FILE)
    public Response<String> uploadFile(MultipartFile file,String attribute,String location,String field) throws Exception {
        byte[] aesKey = EncUtil.getAESKey();
        byte[] fileBytes = EncUtil.aesEncrypt(aesKey,file.getBytes());
        String url = fileOperationService.uploadFile(fileBytes);
        if(url == null) {
            return Response.fail("上传失败");
        }
        byte[] encryptedAesKey = EncUtil.cpabeEncrypt(attribute,aesKey);
        //byte[] encryptedUrl = EncUtil.cpabeEncrypt(attribute,Base64.getDecoder().decode(url));//
        String encryptedAesKeyString = Base64.getEncoder().encodeToString(encryptedAesKey);
        //String encryptedUrlString = Base64.getEncoder().encodeToString(encryptedUrl);//
        //String fileName = EncUtil.contractMd5(file);
        //String fileName = UUID.randomUUID().toString().replace("-","")+"."+ file.getOriginalFilename();
        Long start = System.currentTimeMillis();
        dataAssetFabricService.createDataAsset(FabricIdUtil.generateAssetFabricId(),file.getOriginalFilename(),FabricIdUtil.getMyFabricId(),attribute,location,field,url,encryptedAesKeyString, EncTypeConstant.FULL_FILE);
        Long end = System.currentTimeMillis();
        return Response.success((end-start)+"ms");
    }

    @ApiOperation("下载普通文件")
    @PostMapping(CONFIRMATION + DOWNLOAD_FILE)
    public void downloadFile(@RequestBody Asset asset, HttpServletResponse response) throws Exception {
        try {
            String encryptedAesKeyString = asset.getAesKey();
            byte[] encryptedAesKey = Base64.getDecoder().decode(encryptedAesKeyString);
            byte[] decryptedAesKey = EncUtil.cpabeDecrypt(encryptedAesKey);
            //String encryptedUrlString = asset.getUrl();//
            //byte[] encryptedUrl = Base64.getDecoder().decode(encryptedUrlString);//
            //byte[] decryptedUrl = EncUtil.cpabeDecrypt(encryptedUrl);//
            //String url = Base64.getEncoder().encodeToString(decryptedUrl);//
            byte[] result = fileOperationService.downloadFile(asset.getUrl());
            result = EncUtil.aesDecrypt(decryptedAesKey,result);
            dataAssetOrderFabricService.createDataAssetOrder(FabricIdUtil.generateOrderFabricId(),asset.getFabricId(),FabricIdUtil.getMyFabricId(), OrderStatusConstant.SUCCEEDED);
            response.getOutputStream().write(result);
        }
        catch (Exception e) {
            e.printStackTrace();
            dataAssetOrderFabricService.createDataAssetOrder(FabricIdUtil.generateOrderFabricId(),asset.getFabricId(),FabricIdUtil.getMyFabricId(), OrderStatusConstant.FAILED);
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
    @ApiOperation("上传分片文件")
    @PostMapping(CONFIRMATION + UPLOAD_SHARDED_FILE)
    public Response<String> uploadShardedFile(MultipartFile file,String attribute,String location,String field) throws Exception {
        fileOperationService.saveFile(file);
        List<String> shardNames = VideoUtil.spilt(file.getOriginalFilename());
        for(String shardName : shardNames){
            byte[] aesKey = EncUtil.getAESKey();
            byte[] shardBytes = fileOperationService.getFile(shardName);
            byte[] encryptedShardBytes = EncUtil.aesEncrypt(aesKey,shardBytes);
            String url = fileOperationService.uploadFile(encryptedShardBytes);
            byte[] encryptedAesKey = EncUtil.cpabeEncrypt(attribute,aesKey);
            String encryptedAesKeyString = Base64.getEncoder().encodeToString(encryptedAesKey);
            dataAssetFabricService.createDataAsset(FabricIdUtil.generateAssetFabricId(),shardName,FabricIdUtil.getMyFabricId(),attribute,location,field,url,encryptedAesKeyString, EncTypeConstant.SHARD);
        }
        byte[] aesKey = EncUtil.getAESKey();
        byte[] encryptedFileBytes = EncUtil.aesEncrypt(aesKey,file.getBytes());
        String url = fileOperationService.uploadFile(encryptedFileBytes);
        byte[] encryptedAesKey = EncUtil.cpabeEncrypt(attribute,aesKey);
        String encryptedAesKeyString = Base64.getEncoder().encodeToString(encryptedAesKey);
        dataAssetFabricService.createDataAsset(FabricIdUtil.generateAssetFabricId(),file.getOriginalFilename(),FabricIdUtil.getMyFabricId(),attribute,location,field,url,encryptedAesKeyString, EncTypeConstant.FULL_FILE);
        fileOperationService.deleteFile(file.getOriginalFilename());
        for(String shardName : shardNames){
            fileOperationService.deleteFile(shardName);
        }
        return Response.success("ok");
        /*byte[] aesKey = EncUtil.getAESKey();
        byte[] fileBytes = EncUtil.aesEncrypt(aesKey,file.getBytes());
        int shardNum = Double.valueOf(Math.ceil(fileBytes.length/ UploadConstant.SHARD_SIZE)).intValue();
        List<String> shardUrls = new ArrayList<>();
        for(int i=0;i<shardNum;i++){
            String shardUrl;
            if(i < shardNum -1){
                shardUrl = fileOperationService.uploadFile(Arrays.copyOfRange(fileBytes,i*UploadConstant.SHARD_SIZE,(i + 1)*UploadConstant.SHARD_SIZE));
            }
            else {
                shardUrl = fileOperationService.uploadFile(Arrays.copyOfRange(fileBytes,i*UploadConstant.SHARD_SIZE,fileBytes.length));
            }
            if(shardUrl == null) {
                return Response.fail("上传失败");
            }
            shardUrls.add(shardUrl);
        }
        byte[] urlBytes;
        if(shardNum == 0){
            urlBytes = null;
        }
        else if(shardNum == 1){
            urlBytes = shardUrls.get(0).getBytes();
        }
        else{
            urlBytes = shardUrls.stream().reduce((url,url2)->url+";"+url2).get().getBytes();
        }
        String url = fileOperationService.uploadFile(urlBytes);
        if(url == null) {
            return Response.fail("上传失败");
        }
        byte[] encryptedAesKey = EncUtil.cpabeEncrypt(attribute,aesKey);
        String encryptedAesKeyString = Base64.getEncoder().encodeToString(encryptedAesKey);
        dataAssetFabricService.createDataAsset(FabricIdUtil.generateAssetFabricId(),file.getOriginalFilename(),FabricIdUtil.getMyFabricId(),attribute,location,field,url,encryptedAesKeyString, EncTypeConstant.AES);
        return Response.success("ok");*/
    }
    @ApiOperation("下载分片文件")
    @PostMapping(CONFIRMATION + DOWNLOAD_SHARDED_FILE)
    public void downloadShardedFile(@RequestBody Asset asset, HttpServletResponse response) throws Exception {
        try {
            String encryptedAesKeyString = asset.getAesKey();
            byte[] encryptedAesKey = Base64.getDecoder().decode(encryptedAesKeyString);
            byte[] decryptedAesKey = EncUtil.cpabeDecrypt(encryptedAesKey);
            byte[] result = fileOperationService.downloadFile(asset.getUrl());
            String[] urls = new String(result).split(";");
            byte[][] data = new byte[urls.length][];
            int totalLength = 0;
            for(int i=0;i<urls.length;i++){
                data[i] = fileOperationService.downloadFile(urls[i]);
                totalLength += data[i].length;
            }
            byte[] finalData = new byte[totalLength];
            for(int i=0;i<data.length;i++){
                System.arraycopy(data[i],0,finalData,UploadConstant.SHARD_SIZE * i,data[i].length);
            }
            response.getOutputStream().write(EncUtil.aesDecrypt(decryptedAesKey,finalData));
            dataAssetOrderFabricService.createDataAssetOrder(FabricIdUtil.generateOrderFabricId(),asset.getFabricId(),FabricIdUtil.getMyFabricId(), OrderStatusConstant.SUCCEEDED);
        }
        catch (Exception e) {
            e.printStackTrace();
            dataAssetOrderFabricService.createDataAssetOrder(FabricIdUtil.generateOrderFabricId(),asset.getFabricId(),FabricIdUtil.getMyFabricId(), OrderStatusConstant.FAILED);
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
