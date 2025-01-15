package com.coindesk.coindesk.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CoinDeskService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${coindesk.api.url}")
    String coinDeskApiUrl;

    /*** 串接CoinDesk API獲取原始資料 */
    public JsonNode fetchCoinDeskData() {
        String url = coinDeskApiUrl;
        return restTemplate.getForObject(url, JsonNode.class);
    }

    /*** 重新彙整CoinDesk資料 */
    public Map<String,Object> convertData(JsonNode coinDeskData){
        Map<String,Object> data = new HashMap<>();
        String updatedTime = parseTime(coinDeskData.get("time").path("updatedISO").asText());
        List<Map<String, Object>> currenciesData = parseCurrenciesData(coinDeskData);

        data.put("updatedTime", updatedTime);
        data.put("currenciesData",currenciesData);

        return data;
    }

    /*** 解析原始資料並且輸出幣別、幣別中文名稱、匯率、更新時間等資訊 */
    private List<Map<String, Object>> parseCurrenciesData(JsonNode coinDeskData){
        List<Map<String, Object>> currenciesData = new LinkedList<>();
        coinDeskData.path("bpi").fields().forEachRemaining(entry -> {
            Map<String, Object> curr = new LinkedHashMap<>();
            String currency = entry.getKey();
            String currencyCN = parseCurrencyCN(currency);
            Float rate = entry.getValue().path("rate_float").floatValue();
            curr.put("currency", currency);
            curr.put("currencyCN", currencyCN);
            curr.put("rate", rate);
            currenciesData.add(curr);
        });
        return currenciesData;
    }

    /*** 將幣別代碼轉換為中文名稱 */
    private String parseCurrencyCN(String currency) {
        switch (currency) {
            case "USD":
                return "美元";
            case "EUR":
                return "歐元";
            case "GBP":
                return "英鎊";
            default:
                return currency;
        }
    }

    /*** 解析原始數據的更新時間並將時區更改為GMT+8 */
    private String parseTime(String rawTime){
        try {
            SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
            utcFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            Date utcDate = utcFormat.parse(rawTime);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            return dateFormat.format(utcDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rawTime;
    }
}
