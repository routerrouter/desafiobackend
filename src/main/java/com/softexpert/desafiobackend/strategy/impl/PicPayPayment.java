package com.softexpert.desafiobackend.strategy.impl;

import com.softexpert.desafiobackend.model.Payment;
import com.softexpert.desafiobackend.strategy.PaymentStrategy;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;


@Service
@Data
@AllArgsConstructor
public class PicPayPayment implements PaymentStrategy {

    @Override
    public Payment pay(Payment payment) {
       return  payment.processPayment(this);
    }
}
