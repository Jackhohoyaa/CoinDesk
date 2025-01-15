package com.coindesk.coindesk;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ResponseCode{

    //Http 200
    SUCCESS(200, "成功"),

    //Http 500
    INTERNAL_ERROR(500, "未定義"), // 未被分類的例外產生

    //Http 400
    PARAM_ISSUE(9000, "參數異常"),  // 必填參數未填/參數類型錯誤
    DATA_ISSUE(9003, "資料異常"),   // 資料面問題/資料導致的邏輯錯誤

    //Http 401
    TOKEN_INVALID(9004, "Token異常"),
    LOGIN_ISSUE(9006, "登入異常"),  // 帳號或密碼錯誤(只會在 /auth/login 拋出)//token沒帶/token解析錯誤/token過期

    //Http 403
    FORBIDDEN(9005, "無權限");      // 該帳號無API權限



    private final Integer value;
    private final String message;

}