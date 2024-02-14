package com.ed.coindesk.controller;

import com.ed.coindesk.model.entity.Currency;
import com.ed.coindesk.model.payload.CurrencyCreationRequest;
import com.ed.coindesk.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/currency")
@RestController
public class CurrencyController {
    private final CurrencyService currencyService;

    @GetMapping("/get-currency-name/{code}")
    public ResponseEntity<String> getCurrencyNameBy(@PathVariable("code") String code) {
        log.info("Get a request to get currency name by code: {}", code);
        String name;
        try {
            name = currencyService.getCurrencyNameBy(code);
        } catch (NoSuchElementException e) {
            log.error("Error occur when get currency name by code, errorMsg: {}", e.getMessage(), e);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error occur when get currency name by code, errorMsg: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(name);
    }

    @PostMapping
    public ResponseEntity<Currency> createCurrency(@RequestBody CurrencyCreationRequest request) {
        log.info("Get a request to create currency, request: {}", request);
        Currency currency;
        try {
            currency = currencyService.create(request);
        } catch (Exception e) {
            log.error("Error occur when create currency, errorMsg: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(currency);
    }

    @PatchMapping("/{code}/{name}")
    public ResponseEntity<Currency> updateCurrencyName(
            @PathVariable("code") String code, @PathVariable("name") String name) {
        log.info("Get a request to update currency({})'s name: {}", code, name);

        if (!Optional.ofNullable(code).isPresent() || !Optional.ofNullable(name).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        Currency currency;
        try {
            currency = currencyService.updateName(code, name);
        } catch (Exception e) {
            log.error("Error occur when update currency's name, errorMsg: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok(currency);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Currency> deleteCurrencyBy(@PathVariable String code) {
        log.info("Get a request to delete currency by code: {}", code);
        try {
            currencyService.delete(code);
        } catch (Exception e) {
            log.error("Error occur when delete currency, errorMsg: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }

        return ResponseEntity.ok().build();
    }
}
