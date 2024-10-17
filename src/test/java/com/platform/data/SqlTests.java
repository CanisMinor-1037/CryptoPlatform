package com.platform.data;

import com.google.gson.JsonArray;
import com.platform.data.util.SqlUtil;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;

@SpringBootTest
public class SqlTests {
    @Test
    public void test() {
        boolean result = SqlUtil.testConnect("aaa","aaa","aaa");
        System.out.println(result);
    }
    @Test
    public void test2() {
        boolean result = SqlUtil.testConnect("jdbc:mysql://localhost:3306/data_test?characterEncoding=utf-8&serverTimezone=Asia/Shanghai","root","aaa");
        System.out.println(result);
    }
    @Test
    public void test3() {
        boolean result = SqlUtil.testConnect("jdbc:mysql://localhost:3306/data_test?characterEncoding=utf-8&serverTimezone=Asia/Shanghai","root","1234");
        System.out.println(result);
    }
    @Test
    public void test4() throws SQLException {
        JsonArray result = SqlUtil.executeQuery("jdbc:mysql://localhost:3306/data_test?characterEncoding=utf-8&serverTimezone=Asia/Shanghai",
                "root","1234","select * from data.t_data_asset");
        System.out.println(result);
    }
}
