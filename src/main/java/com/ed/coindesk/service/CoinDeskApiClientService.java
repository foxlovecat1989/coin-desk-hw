package com.ed.coindesk.service;

import com.ed.coindesk.model.payload.CoinDeskQueryBPIResponse;

public interface CoinDeskApiClientService {
    CoinDeskQueryBPIResponse callApi();
}
