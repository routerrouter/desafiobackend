package com.softexpert.desafiobackend.model.enums;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum TipoAcrecimo {
    PERCENTUAL {
        @Override
        public BigDecimal obterAcrescimo(BigDecimal valor, BigDecimal total) {
            return valor.multiply(total).divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP);
        }
    },
    REAIS {
        @Override
        public BigDecimal obterAcrescimo(BigDecimal valor,BigDecimal total) {
            return valor;
        }
    };
    public abstract BigDecimal obterAcrescimo(BigDecimal valor, BigDecimal total);

    @Override
    public String toString() {
        return "TipoAcrecimo{}";
    }
}
