package com.softexpert.desafiobackend.services;

import com.softexpert.desafiobackend.model.Consumidor;
import com.softexpert.desafiobackend.model.dto.ContaDto;

import java.util.ArrayList;

public interface ConsumoService {
    ArrayList<Consumidor>  calcularTotalPagar(ContaDto contaDto);
}
