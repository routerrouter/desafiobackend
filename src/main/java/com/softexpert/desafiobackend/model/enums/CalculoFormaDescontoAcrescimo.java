package com.softexpert.desafiobackend.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum CalculoFormaDescontoAcrescimo {

    PERCENTUAL {
        @Override
        public BigDecimal calculo(BigDecimal valor,BigDecimal total) {
            return valor.multiply(total).divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP);
        }
    },
    REAIS {
        @Override
        public BigDecimal calculo(BigDecimal valor,BigDecimal total) {
            return valor;
        }
    };
    public abstract BigDecimal calculo(BigDecimal valor, BigDecimal total);

    @Override
    public String toString() {
        return "CalculoFormaDescontoAcrescimo{}";
    }
}
