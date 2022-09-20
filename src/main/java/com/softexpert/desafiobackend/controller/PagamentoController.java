package com.softexpert.desafiobackend.controller;

import com.softexpert.desafiobackend.controller.exception.PaymentRequestException;
import com.softexpert.desafiobackend.controller.exception.StatusChangeException;
import com.softexpert.desafiobackend.model.Pagamento;
import com.softexpert.desafiobackend.model.dto.NovoEstadoPagamento;
import com.softexpert.desafiobackend.model.dto.PagamentoGerado;
import com.softexpert.desafiobackend.model.form.PagamentoForm;
import com.softexpert.desafiobackend.model.form.StatusMudadancaForm;
import com.softexpert.desafiobackend.services.PagamentoStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

@RestController
@RequestMapping("/softexpert/pagamento")
public class PagamentoController {
	
	@Autowired
	private SimpMessagingTemplate message;

	@Autowired
	PagamentoStrategy pagamentoStrategy;
	
	@Value("${picpay.url-generate-payment}")
	private String urlGeneratePayment;
	
	@Value("${picpay.url-status-payment}")
	private String urlStatusPayment;
	
	@Value("${picpay.url-callback-payment}")
	private String callbackUrl;
	
	@Value("${picpay.url-return-payment}")
	private String returnUrl;
	
	@Value("${picpay.minutes-for-expiration-payment}")
	private Integer minutesForExpirationPayment;
	
	@Value("${picpay.x-picpay-token}")
	private String picpayToken;
	
	@Value("${picpay.x-seller-token}")
	private String sellerToken;

	
	@PostMapping()
	public ResponseEntity<PagamentoGerado> gerarPagamento(@Valid @RequestBody PagamentoForm form) throws Exception {
		Pagamento pagamento = form.pagamento(callbackUrl, returnUrl, minutesForExpirationPayment);
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("x-picpay-token", picpayToken);

		
		HttpEntity<Pagamento> entity = new HttpEntity<Pagamento>(pagamentoStrategy.pagar(pagamento), headers);
		
		try {
			ResponseEntity<String> response = restTemplate.postForEntity(urlGeneratePayment, entity, String.class);
			return ResponseEntity.ok(new PagamentoGerado(response.getBody()));
		} catch (Exception ex) {
			throw new PaymentRequestException(ex.getMessage());
		}
	}
	
	@PostMapping("/status-changed")
	@ResponseStatus(HttpStatus.OK)
	public void handlePaymentStatusChange(@RequestBody StatusMudadancaForm form) {
		String url = String.format(urlStatusPayment, form.getReferenceId());		
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("x-picpay-token", picpayToken);
		
		HttpEntity<Object> entity = new HttpEntity<>(headers);
		
		try {
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
			NovoEstadoPagamento newStatusPayment = new NovoEstadoPagamento(response.getBody());
			message.convertAndSend(String.format("/queue/%s", newStatusPayment.getReferenceId()), newStatusPayment.getStatus());
		} catch (Exception ex) {
			throw new StatusChangeException(ex.getMessage());
		}
	}
}
