package com.coindesk.coindesk.controller;

import com.coindesk.coindesk.entities.Currency;
import com.coindesk.coindesk.repository.CurrencyRepository;
import com.coindesk.coindesk.service.CurrencyService;
import com.coindesk.coindesk.vo.CurrencyRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.NoSuchElementException;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@SpringBootTest
public class CurrencyControllerTest {

    @Autowired
    private CurrencyController currencyController;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getCurrenciesTest() throws JsonProcessingException {
        //建立模擬資料
        createCurrencyTest();

        //呼叫API
        currencyController.getCurrencies();

        //取得資料
        List<Currency> currencyList = currencyService.getCurrencies();
        assertNotNull(currencyList, "API Response 不應為 NULL");

        //輸出結果
        System.out.println("取得所有貨幣資料:\n" + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(currencyList));
    }

    @Test
    public void createCurrencyTest() {
        //建立資料後呼叫API
        CurrencyRequest currencyEUR = new CurrencyRequest("EUR","歐元");
        currencyController.createCurrency(currencyEUR);

        CurrencyRequest currencyUSD = new CurrencyRequest("USD","美金");
        currencyController.createCurrency(currencyUSD);

        CurrencyRequest currencyGBP = new CurrencyRequest("GBP","英鎊");
        currencyController.createCurrency(currencyGBP);
    }

    @Test
    public void updateCurrencyTest() throws JsonProcessingException {
        //建立模擬資料
        createCurrencyTest();
        CurrencyRequest currencyUSD = new CurrencyRequest("USD","美元");

        //呼叫API
        currencyController.updateCurrency("USD",currencyUSD);

        //取得資料
        Currency currency = currencyRepository.findByCode("USD").orElseThrow(NoSuchElementException::new);
        assertNotNull(currency, "API Response 不應為 NULL");

        //輸出結果
        System.out.println("已更新的貨幣資料:\n" + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(currency));
    }

    @Test
    public void deleteCurrencyTest() throws JsonProcessingException{
        //建立模擬資料
        createCurrencyTest();

        //呼叫API
        currencyController.deleteCurrency("USD");

        //輸出結果
        List<Currency> currencyList = currencyService.getCurrencies();
        System.out.println("取得刪除USD後的貨幣資料:\n" + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(currencyList));
    }
}
