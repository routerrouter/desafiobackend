package com.softexpert.desafiobackend.model;

import com.softexpert.desafiobackend.strategy.PaymentStrategy;
import lombok.Data;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class Payment {

    private Consumer consumer;
    private String expiresAt;
    private String referenceId;
    private String callbackUrl;
    private String returnUrl;

    public Payment(Integer minutesForExpiration) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");
        this.expiresAt = ZonedDateTime.now().plusMinutes(minutesForExpiration).format(formatter);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(this.expiresAt + "\n");
        builder.append(this.referenceId + "\n");
        builder.append(this.callbackUrl + "\n");
        builder.append(this.returnUrl + "\n");
        builder.append(this.expiresAt + "\n");

        builder.append(this.consumer.getNome() + "\n");
        builder.append(this.consumer.getCelular() + "\n");
        builder.append(this.consumer.getEmail() + "\n");
        builder.append(this.consumer.getValorConsumido() + "\n");
        builder.append(this.consumer.getValorPagar() + "\n");

        return builder.toString();
    }

    public Payment processPayment(PaymentStrategy paymentMethod) {
        return paymentMethod.pay(this);
    }

}
