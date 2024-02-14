package com.ed.coindesk.controller;

import com.ed.coindesk.model.payload.CoinDeskQueryBPIResponse;
import com.ed.coindesk.service.CoinDeskApiClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/coin-desk")
@RestController
public class CoinDeskController {
    private final CoinDeskApiClientService service;

    @GetMapping
    public ResponseEntity<CoinDeskQueryBPIResponse> callApi() {
        log.info("Get a request for calling coin desk api");
        CoinDeskQueryBPIResponse response;
        try {
             response = service.callApi();
        } catch (Exception e) {
            log.error("Error occur when call api, errorMsg: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(response);
    }
}
