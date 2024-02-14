package com.ed.coindesk.service;

import com.ed.coindesk.model.entity.Currency;
import com.ed.coindesk.model.payload.CurrencyCreationRequest;
import com.ed.coindesk.repo.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@Service
public class CurrencyServiceImpl implements CurrencyService {
    private final CurrencyRepository repository;

    @Override
    public String getCurrencyNameBy(String code) {
        return findByCode(code).getName();
    }

    @Override
    public Currency findByCode(String code) {
        return repository.findById(code).orElseThrow(() ->
                new NoSuchElementException(
                        String.format("Could not found currency by Code: %s", code)));
    }

    @Override
    public Currency create(CurrencyCreationRequest request) {
        Currency newCurrency = Currency.builder()
                .code(request.getCode())
                .name(request.getName())
                .build();

        return save(newCurrency);
    }

    @Override
    public Currency updateName(String code, String name) {
        Currency origin = findByCode(code);
        origin.setName(name);

        return save(origin);
    }

    @Override
    public void delete(String code) {
        repository.deleteById(code);
    }

    private Currency save(Currency currency) {
        Currency saved = repository.save(currency);
        log.info("Save Currency: {}", saved);

        return saved;
    }
}
