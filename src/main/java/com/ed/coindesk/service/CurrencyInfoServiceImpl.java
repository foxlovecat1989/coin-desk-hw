package com.ed.coindesk.service;

import com.ed.coindesk.model.entity.Currency;
import com.ed.coindesk.model.payload.BPIResponse;
import com.ed.coindesk.model.payload.CoinDeskQueryBPIResponse;
import com.ed.coindesk.model.payload.CurrencyInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class CurrencyInfoServiceImpl implements CurrencyInfoService {
    private final CoinDeskApiClientService coinDeskApiClientService;
    private final CurrencyService currencyService;

    @Override
    public BPIResponse getBPI() {
        CoinDeskQueryBPIResponse response = coinDeskApiClientService.callApi();
        List<BPIResponse.Currency> currencies = getCurrencies(response);
        String updatedTime = response.getTime().getUpdatedUTC()
                .format(DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss"));

        return BPIResponse.builder()
                .currencies(currencies)
                .updatedTime(updatedTime)
                .build();
    }

    @Override
    public String getUpdatedTime() {
        return getBPI().getUpdatedTime();
    }

    @Override
    public CurrencyInfoResponse findByCode(String code) {
        Currency currency = currencyService.findByCode(code);
        List<BPIResponse.Currency> currencies = getBPI().getCurrencies();
        float rate = getRate(currencies, currency);

        return buildResponse(currency, rate);
    }

    private static List<BPIResponse.Currency> getCurrencies(CoinDeskQueryBPIResponse response) {
        CoinDeskQueryBPIResponse.BPI.Currency USD = response.getBpi().getUSD();
        CoinDeskQueryBPIResponse.BPI.Currency EUR = response.getBpi().getEUR();
        CoinDeskQueryBPIResponse.BPI.Currency GBP = response.getBpi().getGBP();

        return Arrays.asList(
                BPIResponse.Currency.builder()
                        .code(USD.getCode())
                        .rate(USD.rateFloat)
                        .build(),
                BPIResponse.Currency.builder()
                        .code(EUR.getCode())
                        .rate(EUR.rateFloat)
                        .build(),
                BPIResponse.Currency.builder()
                        .code(GBP.getCode())
                        .rate(GBP.rateFloat)
                        .build()
        );
    }

    private float getRate(List<BPIResponse.Currency> currencies, Currency currency) {
        return currencies.stream()
                .filter(c -> Objects.equals(c.getCode(), currency.getCode()))
                .findAny().orElseThrow(() -> new NoSuchElementException(
                        String.format("Could not find matching currency by code: %s", currency.getCode())))
                .getRate();
    }

    private CurrencyInfoResponse buildResponse(Currency currency, float rate) {
        return CurrencyInfoResponse.builder()
                .code(currency.getCode())
                .name(currency.getName())
                .rate(rate)
                .build();
    }
}
