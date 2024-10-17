package com.platform.data.controller;

import com.platform.data.common.Response;
import com.platform.data.constant.EncTypeConstant;
import com.platform.data.constant.OrderStatusConstant;
import com.platform.data.entity.dto.Database;
import com.platform.data.entity.po.DBAsset;
import com.platform.data.service.fabricService.DBAssetFabricService;
import com.platform.data.service.fabricService.DBAssetOrderFabricService;
import com.platform.data.service.webService.DBAssetOrderService;
import com.platform.data.service.webService.DBAssetService;
import com.platform.data.util.EncUtil;
import com.platform.data.util.FabricIdUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class DBAssetConfirmationController {
    private final String CONFIRMATION = "/confirmation";
    private final String UPLOAD_DB_ASSET = "/uploadDbAsset";
    private final String DOWNLOAD_DB_ASSET = "/downloadDbAsset";
    private final String DATABASE_AVAILABLE = "/databaseAvailable";
    private final String OWNER_DATABASE = "/ownerDatabase";
    private final String DATABASE_APPLY_AND_APPLIED = "/databaseApplyAndApplied";
    @Autowired
    private DBAssetFabricService dbAssetFabricService;
    @Autowired
    private DBAssetService dbAssetService;
    @Autowired
    private DBAssetOrderService dbAssetOrderService;
    @ApiOperation("获取数据库申请与被申请次数")
    @GetMapping(CONFIRMATION + DATABASE_APPLY_AND_APPLIED)
    public Response<List<List<Long>>> applyAndApplied(){
        List<List<Long>> result = new ArrayList<>();
        List<Long> apply = dbAssetOrderService.applyCountByEachMonth();
        List<Long> applied = dbAssetOrderService.appliedCountByEachMonth();
        result.add(apply);
        result.add(applied);
        return Response.success(result);
    }
    @ApiOperation("获取可申请数据库信息")
    @PostMapping(CONFIRMATION + DATABASE_AVAILABLE)
    public Response<List<DBAsset>> databaseAvailable() {
        return Response.success(dbAssetService.databaseAvailable());
    }
    @ApiOperation("获取自有数据库信息")
    @PostMapping(CONFIRMATION + OWNER_DATABASE)
    public Response<List<DBAsset>> ownerDatabase() {
        return Response.success(dbAssetService.ownerDatabase());
    }
    @ApiOperation("上传数据库")
    @PostMapping(CONFIRMATION + UPLOAD_DB_ASSET)
    public Response<String> uploadDbAsset(String name, String url, String username, String password, String attribute, String location, String field) throws Exception {
        byte[] aesKey = EncUtil.getAESKey();
        byte[] encryptedUrl = EncUtil.aesEncrypt(aesKey,url.getBytes());
        byte[] encryptedUsername = EncUtil.aesEncrypt(aesKey,username.getBytes());
        byte[] encryptedPassword = EncUtil.aesEncrypt(aesKey,password.getBytes());
        byte[] encryptedAesKey = EncUtil.cpabeEncrypt(attribute,aesKey);
        String encryptedUrlString = Base64.getEncoder().encodeToString(encryptedUrl);
        String encryptedUsernameString = Base64.getEncoder().encodeToString(encryptedUsername);
        String encryptedPasswordString = Base64.getEncoder().encodeToString(encryptedPassword);
        String encryptedAesKeyString = Base64.getEncoder().encodeToString(encryptedAesKey);
        Long start = System.currentTimeMillis();
        dbAssetFabricService.createDBAsset(FabricIdUtil.generateDBAssetFabricId(),name,FabricIdUtil.getMyFabricId(),attribute,location,field,encryptedUrlString,encryptedUsernameString,encryptedPasswordString,encryptedAesKeyString, EncTypeConstant.FULL_FILE);
        Long end = System.currentTimeMillis();
        return Response.success((end-start)+"ms");
    }
    @ApiOperation("下载数据库")
    @PostMapping(CONFIRMATION + DOWNLOAD_DB_ASSET)
    public Response<Database> downloadDbAsset(@RequestBody DBAsset dbAsset){
        try{
            String encryptedAesKeyString = dbAsset.getAesKey();
            byte[] encryptedAesKey = Base64.getDecoder().decode(encryptedAesKeyString);
            byte[] decryptedAesKey = EncUtil.cpabeDecrypt(encryptedAesKey);
            String encryptedUrlString = dbAsset.getUrl();
            String encryptedUsernameString = dbAsset.getUsername();
            String encryptedPasswordString = dbAsset.getPassword();
            byte[] encryptedUrl = Base64.getDecoder().decode(encryptedUrlString);
            byte[] encryptedUsername = Base64.getDecoder().decode(encryptedUsernameString);
            byte[] encryptedPassword = Base64.getDecoder().decode(encryptedPasswordString);
            byte[] decryptedUrl = EncUtil.aesDecrypt(decryptedAesKey,encryptedUrl);
            byte[] decryptedUsername = EncUtil.aesDecrypt(decryptedAesKey,encryptedUsername);
            byte[] decryptedPassword = EncUtil.aesDecrypt(decryptedAesKey,encryptedPassword);
            String url = new String(decryptedUrl);
            String username = new String(decryptedUsername);
            String password = new String(decryptedPassword);
            Database database = new Database(url,username,password);
            return Response.success(database);
        }
        catch (Exception e){
            e.printStackTrace();
            return Response.fail(null);
        }
    }
}
