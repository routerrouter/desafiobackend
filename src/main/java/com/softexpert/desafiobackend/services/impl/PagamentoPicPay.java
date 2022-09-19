package com.softexpert.desafiobackend.services.impl;

import com.softexpert.desafiobackend.model.Pagamento;
import com.softexpert.desafiobackend.services.PagamentoService;

public class PagamentoPicPay  implements PagamentoService {
    @Override
    public Pagamento pagar(String callbackUrl, String returnUrl, Integer minutesForExpiration) {
        System.out.println("Pagou pelo PicPay");
        return null;
    }
}
