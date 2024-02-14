package com.ed.coindesk.controller;

import com.ed.coindesk.model.payload.CurrencyInfoResponse;
import com.ed.coindesk.service.CurrencyInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/currency-info")
@RestController
public class CurrencyInfoController {
    private final CurrencyInfoService service;

    @GetMapping("/get-updated-time")
    public ResponseEntity<String> getUpdatedTime() {
        log.info("Get a request to get updated time");
        String updatedTime;
        try {
            updatedTime = service.getUpdatedTime();
            log.info("updatedTime: {}", updatedTime);
        } catch (Exception e) {
            log.error("Error occur when get updated time, errorMsg: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(updatedTime);
    }

    @GetMapping("/{code}")
    public ResponseEntity<CurrencyInfoResponse> findCurrencyInfoBy(@PathVariable("code") String code) {
        log.info("Get a request to find CurrencyInfo by code: {}", code);
        CurrencyInfoResponse currencyInfo;
        try {
            currencyInfo = service.findByCode(code);
        } catch (Exception e) {
            log.error("Error occur when find CurrencyInfo by code, errorMsg: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(currencyInfo);
    }
}
