package com.softexpert.desafiobackend.model;

import lombok.Data;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class Pagamento {

    private String referenceId;
    private String callbackUrl;
    private String returnUrl;
    private String expiresAt;
    private Consumidor consumidor;

    public Pagamento(Integer minutesForExpiration) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ");
        this.expiresAt = ZonedDateTime.now().plusMinutes(minutesForExpiration).format(formatter);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(this.referenceId + "\n");
        builder.append(this.callbackUrl + "\n");
        builder.append(this.returnUrl + "\n");
        builder.append(this.expiresAt + "\n");

        builder.append(this.consumidor.getNome() + "\n");
        builder.append(this.consumidor.getCelular() + "\n");
        builder.append(this.consumidor.getEmail() + "\n");
        builder.append(this.consumidor.getValorConsumido() + "\n");
        builder.append(this.consumidor.getValorPagar() + "\n");

        return builder.toString();
    }

}
