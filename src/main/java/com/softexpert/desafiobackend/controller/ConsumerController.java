package com.softexpert.desafiobackend.controller;


import com.softexpert.desafiobackend.model.Consumer;
import com.softexpert.desafiobackend.model.dto.CountDto;
import com.softexpert.desafiobackend.model.form.PicPayPaymentForm;
import com.softexpert.desafiobackend.strategy.ConsumoService;
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
public class ConsumerController {

    @Autowired
    ConsumoService contaService;

    private List<Consumer> pessoasConsumidoras = new ArrayList<>();

    @PostMapping()
    public ResponseEntity<Object> saveConsume(@RequestBody CountDto contaDto) throws Exception {
        PicPayPaymentForm paymentForm = new PicPayPaymentForm();
        List<Consumer> consumos = contaService.totalAcount(contaDto);
        pessoasConsumidoras = consumos;
        for ( Consumer pessoa : consumos) {
            Link selfLink = linkTo(methodOn(ConsumerController.class)
                  .getContaIndividual(pessoa.getNome())).withSelfRel();

            paymentForm.setConsumer(pessoa);
            Link paymentLink = linkTo(methodOn(PaymentController.class)
                    .generatePaymentPicPay(paymentForm)).withSelfRel();

            pessoa.add(paymentLink);
            pessoa.add(selfLink);
        }
        Link link = linkTo(ConsumerController.class).withSelfRel();
        CollectionModel<Consumer> result = CollectionModel.of(consumos, link);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping
    public ResponseEntity<Object> getContaIndividual(@RequestParam String nome) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoasConsumidoras.stream().filter( p-> p.getNome().equals(nome)));
    }
}
