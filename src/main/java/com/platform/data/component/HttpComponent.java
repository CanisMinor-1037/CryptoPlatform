package com.platform.data.component;

import com.platform.data.common.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
public class HttpComponent {
    @Value("${http.ip}")
    private String ip;
    @Value("${http.port}")
    private int port;
    private RestTemplate restTemplate = new RestTemplate();
    private String getPublicKeyUrl(){
        return "http://" + ip + ":" + port + "/api/getPublicKey";
    }
    private String getPrivateKeyUrl(String attribute){
        return "http://" + ip + ":" + port + "/api/getPrivateKey?attribute=" + attribute;
    }

    public byte[] getPrivateKey(String attribute) throws IOException {
        return restTemplate.getForObject(getPrivateKeyUrl(attribute), byte[].class);
    }

    public byte[] getPublicKey() throws IOException {
        return restTemplate.getForObject(getPublicKeyUrl(), byte[].class);
    }
}
