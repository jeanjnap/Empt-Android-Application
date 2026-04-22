package com.example.exemple.payment.othrer;

import java.io.Serializable;

public enum TipoCartaoEnum implements Serializable {
    CREDITO_A_VISTA(1),
    CREDITO_PARCELADO(2),
    CREDITO_PARCELADO_EMISSOR(3),
    DEBITO(4),
    VOUCHER(5);

    private Integer value;

    TipoCartaoEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
