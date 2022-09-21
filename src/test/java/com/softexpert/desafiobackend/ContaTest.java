package com.softexpert.desafiobackend;

import com.softexpert.desafiobackend.model.Consumer;
import com.softexpert.desafiobackend.model.dto.CountDto;
import com.softexpert.desafiobackend.model.enums.CalculoFormaDescontoAcrescimo;
import com.softexpert.desafiobackend.strategy.ConsumoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
public class ContaTest {

    @Autowired
    ConsumoService contaService;

    @Test
    public void totalAPagarPorPessoa() {

        ArrayList<Consumer> espected = new ArrayList<>();
        espected.add(new Consumer("Rufino","","",BigDecimal.valueOf(42),BigDecimal.valueOf(31.92)));
        espected.add(new Consumer("Danilson","","",BigDecimal.valueOf(8),BigDecimal.valueOf(6.08)));

        ArrayList<Consumer> consumidores = getPessoas();
        CountDto dto = new CountDto(consumidores,BigDecimal.valueOf(20),BigDecimal.valueOf(8),BigDecimal.valueOf(0), CalculoFormaDescontoAcrescimo.REAIS);

        consumidores = contaService.totalAcount(dto);
        Assertions.assertEquals(consumidores,espected);

    }

    private ArrayList<Consumer> getPessoas() {
        Consumer rufino = new Consumer("Rufino", BigDecimal.valueOf(42));
        Consumer danilson = new Consumer("Danilson", BigDecimal.valueOf(8));

        ArrayList<Consumer> consumidores = new ArrayList<>();
        consumidores.add(rufino);
        consumidores.add(danilson);
        return consumidores;
    }
}
