package com.ed.coindesk.service;

import com.ed.coindesk.model.entity.Currency;
import com.ed.coindesk.model.payload.CurrencyCreationRequest;

public interface CurrencyService {
    String getCurrencyNameBy(String code);

    Currency findByCode(String code);

    Currency create(CurrencyCreationRequest request);

    Currency updateName(String code, String name);

    void delete(String code);
}
