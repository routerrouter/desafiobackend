package com.softexpert.desafiobackend.model;

import com.softexpert.desafiobackend.services.impl.ConsumoServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Calculo {
    public static void main(String[] args) {
       Consumidor rufino = new Consumidor("Rufino", BigDecimal.valueOf(42));
       Consumidor danilson = new Consumidor("Danilson", BigDecimal.valueOf(8));
       Consumidor amarildo = new Consumidor("Amarildo", BigDecimal.valueOf(7));

       ArrayList<Consumidor> consumidores = new ArrayList<>();
       ConsumoServiceImpl conta = new ConsumoServiceImpl();

       consumidores.add(rufino);
       consumidores.add(danilson);
       //consumidores.add(amarildo);

       //System.out.println(conta.calcularTotalPagar(consumidores,BigDecimal.valueOf(34.48),BigDecimal.valueOf(8),BigDecimal.valueOf(0), CalculoFormaDescontoAcrescimo.PERCENTUAL));
    }
}
