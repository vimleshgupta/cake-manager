package com.waracle.cakemanagerservice.client;

import com.waracle.cakemanagerservice.client.response.CakeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CakeAPIClient {

    @Autowired
    private RestTemplate restTemplate;
    @Value("${cakeapi.url}")
    private String url;

    public ResponseEntity<CakeResponse[]> getCakes() {

        return restTemplate.getForEntity(url, CakeResponse[].class);
    }

}
