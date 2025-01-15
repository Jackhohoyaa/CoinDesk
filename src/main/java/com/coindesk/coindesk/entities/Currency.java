package com.coindesk.coindesk.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@Table(name = "CURRENCY")
@NoArgsConstructor
@AllArgsConstructor
public class Currency {

    @Id
    @Column(name = "CODE", nullable = false ,length = 30)
    private String code;

    @Column(name = "CN_Name", nullable = false, length = 50)
    private String cnName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }
}
