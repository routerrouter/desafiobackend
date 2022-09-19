package com.softexpert.desafiobackend.services;

import com.softexpert.desafiobackend.model.Pagamento;

public interface PagamentoService {
    Pagamento pagar(String callbackUrl, String returnUrl, Integer minutesForExpiration);
}
