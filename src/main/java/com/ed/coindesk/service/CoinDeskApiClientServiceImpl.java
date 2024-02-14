package com.ed.coindesk.service;

import com.ed.coindesk.model.payload.CoinDeskQueryBPIResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Slf4j
@Service
public class CoinDeskApiClientServiceImpl implements CoinDeskApiClientService {
    @Value("${coinDesk.bpiUrl}")
    private String bpiUrl;

    private final RestTemplate restTemplate;

    public CoinDeskApiClientServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(10L))
                .build();
    }

    @Override
    public CoinDeskQueryBPIResponse callApi() {
        log.info("Call Api: {}", bpiUrl);

        CoinDeskQueryBPIResponse response;
        try {
            response = restTemplate.getForObject(bpiUrl, CoinDeskQueryBPIResponse.class);
            log.info("response: {}", response);
        } catch (Exception e) {
            throw new IllegalStateException(String.format("Error occur when call: %s", bpiUrl), e);
        }

        return response;
    }
}
