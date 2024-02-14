package com.ed.coindesk.service;

import com.ed.coindesk.model.payload.BPIResponse;
import com.ed.coindesk.model.payload.CurrencyInfoResponse;

public interface CurrencyInfoService {
    String getUpdatedTime();
    CurrencyInfoResponse findByCode(String code);
    BPIResponse getBPI();
}
