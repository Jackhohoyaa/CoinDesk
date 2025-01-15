package com.coindesk.coindesk.controller;


import com.coindesk.coindesk.entities.Currency;
import com.coindesk.coindesk.service.CoinDeskService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/coinDesk")
public class CoinDeskController {

    @Autowired
    private CoinDeskService coinDeskService;

    @GetMapping
    public ResponseEntity<Object> fetchCoinDeskData() {
        JsonNode result = coinDeskService.fetchCoinDeskData();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/converted")
    public ResponseEntity<Object> getConvertedData() {
        JsonNode rawData = coinDeskService.fetchCoinDeskData();
        Map<String,Object> result = coinDeskService.convertData(rawData);
        return ResponseEntity.ok().body(result);
    }


}
