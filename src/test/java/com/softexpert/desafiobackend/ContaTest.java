package com.softexpert.desafiobackend;

import com.softexpert.desafiobackend.model.Consumidor;
import com.softexpert.desafiobackend.model.dto.ContaDto;
import com.softexpert.desafiobackend.model.enums.CalculoFormaDescontoAcrescimo;
import com.softexpert.desafiobackend.services.ConsumoService;
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

        ArrayList<Consumidor> espected = new ArrayList<>();
        espected.add(new Consumidor("Rufino","","",BigDecimal.valueOf(42),BigDecimal.valueOf(31.92)));
        espected.add(new Consumidor("Danilson","","",BigDecimal.valueOf(8),BigDecimal.valueOf(6.08)));

        ArrayList<Consumidor> consumidores = getPessoas();
        ContaDto dto = new ContaDto(consumidores,BigDecimal.valueOf(20),BigDecimal.valueOf(8),BigDecimal.valueOf(0), CalculoFormaDescontoAcrescimo.REAIS);

        consumidores = contaService.calcularTotalPagar(dto);
        Assertions.assertEquals(consumidores,espected);

    }

    private ArrayList<Consumidor> getPessoas() {
        Consumidor rufino = new Consumidor("Rufino", BigDecimal.valueOf(42));
        Consumidor danilson = new Consumidor("Danilson", BigDecimal.valueOf(8));

        ArrayList<Consumidor> consumidores = new ArrayList<>();
        consumidores.add(rufino);
        consumidores.add(danilson);
        return consumidores;
    }
}
