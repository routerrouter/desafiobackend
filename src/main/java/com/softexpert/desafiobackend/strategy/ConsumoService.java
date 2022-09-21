package com.softexpert.desafiobackend.strategy;

import com.softexpert.desafiobackend.model.Consumer;
import com.softexpert.desafiobackend.model.dto.CountDto;

import java.util.ArrayList;

public interface ConsumoService {
    ArrayList<Consumer> totalAcount(CountDto contaDto);
}
