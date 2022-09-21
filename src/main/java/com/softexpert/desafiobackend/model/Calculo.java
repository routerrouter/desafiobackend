package com.softexpert.desafiobackend.model;

import com.softexpert.desafiobackend.strategy.impl.ConsumoServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Calculo {
    public static void main(String[] args) {
       Consumer rufino = new Consumer("Rufino", BigDecimal.valueOf(42));
       Consumer danilson = new Consumer("Danilson", BigDecimal.valueOf(8));
       Consumer amarildo = new Consumer("Amarildo", BigDecimal.valueOf(7));

       ArrayList<Consumer> consumidores = new ArrayList<>();
       ConsumoServiceImpl conta = new ConsumoServiceImpl();

       consumidores.add(rufino);
       consumidores.add(danilson);
       //consumidores.add(amarildo);

       //System.out.println(conta.calcularTotalPagar(consumidores,BigDecimal.valueOf(34.48),BigDecimal.valueOf(8),BigDecimal.valueOf(0), CalculoFormaDescontoAcrescimo.PERCENTUAL));
    }
}
