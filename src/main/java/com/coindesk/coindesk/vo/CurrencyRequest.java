package com.coindesk.coindesk.vo;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
public class CurrencyRequest {

    private String code;

    private String cnName;

    public CurrencyRequest(String code, String cnName) {
        this.code = code;
        this.cnName = cnName;
    }

    public @NotBlank(message = "code參數不可為null或空字串") @Size(max = 30, message = "code參數不可超過30個字元") String getCode() {
        return code;
    }

    public @NotBlank(message = "cnName參數不可為null或空字串") @Size(max = 50, message = "cnName參數不可超過50個字元") String getCnName() {
        return cnName;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }
}
