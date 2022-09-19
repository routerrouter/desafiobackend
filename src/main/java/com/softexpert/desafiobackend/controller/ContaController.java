package com.softexpert.desafiobackend.controller;


import com.softexpert.desafiobackend.model.Consumidor;
import com.softexpert.desafiobackend.model.dto.ContaDto;
import com.softexpert.desafiobackend.services.ConsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/softexpert/conta")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ContaController {

    @Autowired
    ConsumoService contaService;

    private List<Consumidor> pessoasConsumidoras = new ArrayList<>();

    @PostMapping()
    public ResponseEntity<Object> saveCourse(@RequestBody ContaDto contaDto){
        List<Consumidor> consumos = contaService.calcularTotalPagar(contaDto);
        pessoasConsumidoras = consumos;
        for ( Consumidor pessoa : consumos) {
            Link selfLink = linkTo(methodOn(ContaController.class)
                  .getContaIndividual(pessoa.getNome())).withSelfRel();
            pessoa.add(selfLink);
        }
        Link link = linkTo(ContaController.class).withSelfRel();
        CollectionModel<Consumidor> result = CollectionModel.of(consumos, link);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<Object> getContaIndividual(@RequestParam String nome) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoasConsumidoras.stream().filter( p-> p.getNome().equals(nome)));
    }
}
