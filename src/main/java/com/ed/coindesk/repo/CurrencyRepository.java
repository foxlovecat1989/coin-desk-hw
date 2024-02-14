package com.ed.coindesk.repo;

import com.ed.coindesk.model.entity.Currency;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, String> {
}
