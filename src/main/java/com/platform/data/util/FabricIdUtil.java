package com.platform.data.util;

import com.platform.data.constant.FabricIdConstant;
import com.platform.data.constant.RedisConstant;
import org.springframework.data.redis.core.RedisTemplate;


public class FabricIdUtil {
    private static RedisTemplate redisTemplate;
    private static int departmentHash;
    private static String myFabricId;
    public static void setRedisTemplate(RedisTemplate redisTemplate) {
        FabricIdUtil.redisTemplate = redisTemplate;
    }
    public static void setDepartmentHash(int departmentHash) {
        FabricIdUtil.departmentHash = departmentHash;
    }
    private static String generateUniqueFabricId() {
        String redisKey = RedisConstant.FABRIC_ID_PREFIX;
        Long id = redisTemplate.opsForValue().increment(redisKey);
        int myDepartmentIdNum = Integer.parseInt(myFabricId.substring(FabricIdConstant.DEPARTMENT_PREFIX.length()));
        String result = String.format("%08d%d", myDepartmentIdNum, id + 2000L);
        return result;
    }
    public static String generateDepartmentFabricId(){
        return FabricIdConstant.DEPARTMENT_PREFIX + generateUniqueFabricId() ;
    }
    public static String generateAssetFabricId(){
        return FabricIdConstant.ASSET_PREFIX + generateUniqueFabricId() ;
    }
    public static String generateOrderFabricId(){
        return FabricIdConstant.ORDER_PREFIX + generateUniqueFabricId() ;
    }
    public static String generateAttributeApplicationFabricId(){
        return FabricIdConstant.ATTRIBUTE_APPLICATION_PREFIX + generateUniqueFabricId() ;
    }
    public static String getAttributeFabricId(){
        return FabricIdConstant.ATTRIBUTE_PREFIX + generateUniqueFabricId() ;
    }
    public static String generateDBAssetFabricId(){
        return FabricIdConstant.DB_ASSET_PREFIX + generateUniqueFabricId() ;
    }
    public static String generateVideoAssetFabricId(){
        return FabricIdConstant.VIDEO_ASSET_PREFIX + generateUniqueFabricId() ;
    }
    public static String generateDBAssetOrderFabricId(){
        return FabricIdConstant.DB_ASSET_ORDER_PREFIX + generateUniqueFabricId() ;
    }
    public static String generateVideoAssetOrderFabricId(){
        return FabricIdConstant.VIDEO_ASSET_ORDER_PREFIX + generateUniqueFabricId();
    }
    public static String getMyFabricId(){
        return myFabricId;
    }
    public static void setMyFabricId(String departmentFabricId) {
        FabricIdUtil.myFabricId = departmentFabricId;
    }

}
