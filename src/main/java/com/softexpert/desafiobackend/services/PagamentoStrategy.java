package com.softexpert.desafiobackend.services;

import com.softexpert.desafiobackend.model.Pagamento;

public interface PagamentoStrategy {
    Pagamento pagar(Pagamento pagamento);
}
