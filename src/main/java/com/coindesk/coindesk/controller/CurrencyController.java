package com.coindesk.coindesk.controller;

import com.coindesk.coindesk.ResponseCode;
import com.coindesk.coindesk.entities.Currency;
import com.coindesk.coindesk.service.CurrencyService;
import com.coindesk.coindesk.vo.CurrencyRequest;
import com.coindesk.coindesk.vo.ResponseMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    public ResponseEntity<Object> getCurrencies() {
        List<Currency> result = currencyService.getCurrencies();
        return ResponseEntity.ok().body(new ResponseMsg(ResponseCode.SUCCESS.getValue(), "取得貨幣資料成功", result));
    }

    @PostMapping
    public ResponseEntity<Object> createCurrency(@RequestBody @Valid CurrencyRequest request) {
        currencyService.createCurrency(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMsg(ResponseCode.CREATE.getValue(), "新增貨幣資料成功"));
    }

    @PatchMapping("/{code}")
    public ResponseEntity<Object> updateCurrency(@PathVariable String code, @RequestBody @Valid CurrencyRequest request) {
        Currency result = currencyService.updateCurrency(code, request);
        return ResponseEntity.ok().body(new ResponseMsg(ResponseCode.SUCCESS.getValue(), "更新貨幣資料成功", result));
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Object> deleteCurrency(@PathVariable String code) {
        currencyService.deleteCurrency(code);
        return ResponseEntity.ok().body(new ResponseMsg(ResponseCode.SUCCESS.getValue(), "刪除貨幣資料成功"));
    }
}
