package com.coindesk.coindesk.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ResponseCode{

    //Http 200
    SUCCESS(200, "成功"),

    //Http 201
    CREATE(201,"成功建立"),

    //Http 500
    INTERNAL_ERROR(500, "未定義"), // 未被分類的例外產生

    //Http 400
    PARAM_ISSUE(9000, "參數異常"),  // 必填參數未填/參數類型錯誤
    DATA_ISSUE(9003, "資料異常");   // 資料面問題/資料導致的邏輯錯誤

    private final Integer value;
    private final String message;

    ResponseCode(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public Integer getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}