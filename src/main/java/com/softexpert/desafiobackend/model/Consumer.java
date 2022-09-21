package com.softexpert.desafiobackend.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Consumer extends RepresentationModel <Consumer> {

    private String nome;
    private String email;
    private String celular;
    private BigDecimal valorConsumido;
    private BigDecimal valorPagar;



    public Consumer(String nome , BigDecimal consumo) {
        this.nome = nome;
        this.valorConsumido = consumo;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", celular='" + celular + '\'' +
                ", valorConsumido=" + valorConsumido +
                ", valorPagar=" + valorPagar +
                '}';
    }
}
