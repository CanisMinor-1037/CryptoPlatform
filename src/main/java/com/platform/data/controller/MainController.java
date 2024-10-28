package com.platform.data.controller;

import com.platform.data.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@CrossOrigin(origins = "*")
public class MainController {
    private final String MAIN = "/main";
    private final String IP = "/ip";
    private final String DEPARTMENT = "/department";
    @Value("${department.name}")
    private String departmentName;
    @ApiOperation("获取本机ip地址")
    @GetMapping(MAIN + IP)
    public Response<String> ip() throws UnknownHostException {
        return Response.success(InetAddress.getLocalHost().toString());
    }
    @ApiOperation("获取本机构名")
    @GetMapping(MAIN + DEPARTMENT)
    public Response<String> department(){
        return Response.success(departmentName);
    }
}
