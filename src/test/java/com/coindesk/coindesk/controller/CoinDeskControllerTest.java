package com.coindesk.coindesk.controller;

import com.coindesk.coindesk.service.CoinDeskService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@Slf4j
public class CoinDeskControllerTest {

    @Autowired
    private CoinDeskController coinDeskController;

    @Autowired
    private CoinDeskService coinDeskService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void fetchCoinDeskDataTest() {
        //呼叫API
        coinDeskController.fetchCoinDeskData();

        //取得資料
        JsonNode response = coinDeskService.fetchCoinDeskData();

        //驗證資料不為NULL
        assertNotNull(response, "API Response 不應為 NULL");

        //輸出資料
        System.out.println("取得 CoinDesk API 資訊:\n" + coinDeskService.fetchCoinDeskData().toPrettyString());
    }

    @Test
    public void convertDataTest() throws JsonProcessingException {
        //呼叫API
        coinDeskController.getConvertedData();

        //取得資料
        Map<String,Object> response = coinDeskService.convertData(coinDeskService.fetchCoinDeskData());

        //驗證資料不為NULL
        assertNotNull(response, "API Response 不應為 NULL");

        //輸出資料
        System.out.println("轉換後的 CoinDesk 資訊:\n" + objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
    }
}
