package com.coindesk.coindesk.service;

import com.coindesk.coindesk.entities.Currency;
import com.coindesk.coindesk.repository.CurrencyRepository;
import com.coindesk.coindesk.vo.CurrencyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    /*** 取得貨幣 */
    public List<Currency> getCurrencies() {
        return currencyRepository.findAll();
    }

    /***  新增貨幣 */
    public void createCurrency(CurrencyRequest request) {
        Currency currency = new Currency();
        currency.setCode(request.getCode());
        currency.setCnName(request.getCnName());
        currencyRepository.save(currency);
    }

    /*** 更新貨幣 */
    public Currency updateCurrency(String code, CurrencyRequest request) {
        Optional<Currency> existingCurrency = Optional.ofNullable(currencyRepository.findByCode(code)
                .orElseThrow(() -> new NoSuchElementException("查無資料")));
        if (existingCurrency.isPresent()) {
            Currency currency = existingCurrency.get();
            currency.setCnName(request.getCnName());
            currencyRepository.save(currency);
            return currency;
        }
        return null;
    }

    /*** 刪除貨幣 */
    public void deleteCurrency(String code) {
        Optional<Currency> existingCurrency = Optional.ofNullable(currencyRepository.findByCode(code)
                .orElseThrow(() -> new NoSuchElementException("查無資料")));
        existingCurrency.ifPresent(currency -> currencyRepository.delete(currency));
    }
}
