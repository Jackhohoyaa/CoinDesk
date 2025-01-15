CREATE TABLE IF NOT EXISTS currency (
    id BIGINT AUTO_INCREMENT NOT NULL COMMENT '貨幣id',
    code NVARCHAR(30) NOT NULL COMMENT '貨幣代號',
    cnName NVARCHAR(50) NOT NULL COMMENT '貨幣中文名稱',
    PRIMARY KEY (id),
    UNIQUE KEY uk_currency_code (code)
    );

