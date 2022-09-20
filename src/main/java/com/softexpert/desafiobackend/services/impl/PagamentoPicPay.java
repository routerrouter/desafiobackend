package com.softexpert.desafiobackend.services.impl;

import com.softexpert.desafiobackend.model.Pagamento;
import com.softexpert.desafiobackend.services.PagamentoStrategy;
import org.springframework.stereotype.Service;

@Service
public class PagamentoPicPay  implements PagamentoStrategy {

    @Override
    public Pagamento pagar(Pagamento pagamento) {
       return  pagamento.processarPagamento(this::pagar);
    }
}
