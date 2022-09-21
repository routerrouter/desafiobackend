package com.softexpert.desafiobackend.strategy.impl;

import com.softexpert.desafiobackend.model.Consumer;
import com.softexpert.desafiobackend.model.dto.CountDto;
import com.softexpert.desafiobackend.model.enums.CalculoFormaDescontoAcrescimo;
import com.softexpert.desafiobackend.strategy.ConsumoService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

@Service
public class ConsumoServiceImpl implements ConsumoService {

    private BigDecimal subtotal;


    @Override
    public ArrayList<Consumer> totalAcount(CountDto contaDto) {

        subtotal = contaDto.getConsumos().stream()
                .map(item -> item.getValorConsumido())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        ArrayList<Consumer> pessoa = new ArrayList<>();

        contaDto.getConsumos().forEach( consumo -> {
            pessoa.add(getConsumoPessoa(consumo,contaDto.getDesconto(),contaDto.getFrete(),contaDto.getAcrescimo(),contaDto.getFormaDescontoAcrescimo()));
        });

        return pessoa;
    }

    private Consumer getConsumoPessoa(Consumer consumo, BigDecimal desconto, BigDecimal frete, BigDecimal acrescimo, CalculoFormaDescontoAcrescimo formaDescontoAcrescimo) {
        Consumer pessoaConsumidor = new Consumer();
        BigDecimal total = subtotal.add(frete);
        BigDecimal percentualSubtotal = consumo.getValorConsumido().divide(subtotal,2, RoundingMode.HALF_UP);
        BigDecimal freteIndividual = frete.multiply(percentualSubtotal);
        BigDecimal descontoIndividual = getDesconto(desconto,total,formaDescontoAcrescimo).multiply(percentualSubtotal);
        BigDecimal acrescimoIndividual = getAcrescimo(acrescimo,total,formaDescontoAcrescimo).multiply(percentualSubtotal);
        BigDecimal pTotal = consumo.getValorConsumido().add(freteIndividual).add(acrescimoIndividual).subtract(descontoIndividual);
        pessoaConsumidor.setValorPagar(pTotal.setScale(2, RoundingMode.HALF_UP));
        pessoaConsumidor.setNome(consumo.getNome());
        pessoaConsumidor.setEmail(consumo.getEmail());
        pessoaConsumidor.setCelular(consumo.getCelular());
        pessoaConsumidor.setValorConsumido(consumo.getValorConsumido());

        return pessoaConsumidor;
    }

    private BigDecimal getDesconto(BigDecimal desconto,BigDecimal subtotalConta, CalculoFormaDescontoAcrescimo formaDesconto) {
        return formaDesconto.calculo(desconto,subtotalConta);
    }

    private BigDecimal getAcrescimo(BigDecimal acrescimo,BigDecimal subtotalConta, CalculoFormaDescontoAcrescimo formaAcrescimo) {
        return formaAcrescimo.calculo(acrescimo,subtotalConta);
    }
}
