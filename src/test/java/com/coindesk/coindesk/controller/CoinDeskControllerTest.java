package com.coindesk.coindesk.controller;

import com.coindesk.coindesk.service.CoinDeskService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@Slf4j
public class CoinDeskControllerTest {

    @Autowired
    private CoinDeskService coinDeskService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${coindesk.api.url}")
    private String apiUrl;

    @Test
    public void fetchCoinDeskDataTest() {
        // Arrange: 使用 mock 物件來替代實際的 coinDeskService
        CoinDeskService mockCoinDeskService = mock(CoinDeskService.class);
        JsonNode mockResponse = mock(JsonNode.class); // 使用 mock 的 JsonNode

        // 設定 mock 回應
        when(mockCoinDeskService.fetchCoinDeskData()).thenReturn(mockResponse);

        // Act: 呼叫 mock 的 fetchCoinDeskData 方法
        JsonNode response = mockCoinDeskService.fetchCoinDeskData();

        // 驗證是否被調用一次
        verify(mockCoinDeskService, times(1)).fetchCoinDeskData();

        // Assert
        assertNotNull(response, "Response should not be null");

        System.out.println(coinDeskService.fetchCoinDeskData().toString());
    }


    @Test
    public void convertDataTest() {
    }
}
