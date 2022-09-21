package com.softexpert.desafiobackend.model.dto;

import com.softexpert.desafiobackend.model.Consumer;
import com.softexpert.desafiobackend.model.enums.CalculoFormaDescontoAcrescimo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;

@Data
@AllArgsConstructor
public class CountDto {
    private ArrayList<Consumer> consumos;
    private BigDecimal desconto;
    private BigDecimal frete;
    private BigDecimal acrescimo;
    private CalculoFormaDescontoAcrescimo formaDescontoAcrescimo;

}
