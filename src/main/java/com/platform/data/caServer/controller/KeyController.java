package com.platform.data.caServer.controller;

import com.platform.data.caServer.service.KeyService;
import com.platform.data.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
public class KeyController {
    @Autowired
    private KeyService keyService;
    @GetMapping("/getPublicKey")
    public byte[] getPublickey() throws IOException, ClassNotFoundException {
        return keyService.getPublicKey();
    }
    @GetMapping("/getPrivateKey")
    public byte[] getPrivatekey(String attribute) throws IOException, ClassNotFoundException, NoSuchAlgorithmException {
        return keyService.getPrivateKey(attribute);
    }
}
