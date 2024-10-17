package com.platform.data.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.sql.*;

public class SqlUtil {
    public static boolean testConnect(String url,String username,String password){
        try{
            Connection con = DriverManager.getConnection(url, username, password);
            con.close();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public static JsonArray executeQuery(String url,String username,String password,String sql) throws SQLException {
        Connection con = null;
        try{
            con = DriverManager.getConnection(url, username, password);
            Statement stmt = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery(sql);
            // json数组
            JsonArray array = new JsonArray();

            // 获取列数
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            // 遍历ResultSet中的每条数据
            while (rs.next()) {
                JsonObject jsonObj = new JsonObject();
                // 遍历每一列
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnLabel(i);
                    String value = rs.getString(columnName);
                    jsonObj.addProperty(columnName, value);
                }
                array.add(jsonObj);
            }
            return array;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        finally {
            con.close();
        }
    }
}
