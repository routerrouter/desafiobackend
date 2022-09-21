package com.softexpert.desafiobackend.strategy;

import com.softexpert.desafiobackend.model.Payment;

public interface PaymentStrategy {
    Payment pay(Payment payment);
}
