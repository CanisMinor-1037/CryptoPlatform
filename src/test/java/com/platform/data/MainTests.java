package com.platform.data;

import com.platform.data.controller.MainController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.UnknownHostException;

@SpringBootTest
public class MainTests {
    @Autowired
    private MainController controller;
    @Test
    public void test() throws UnknownHostException {
        System.out.println(controller.ip().getData());
    }
    @Test
    public void test2() {
        System.out.println(controller.department().getData());
    }
}
