package com.softexpert.desafiobackend.model.form;


import com.softexpert.desafiobackend.model.Consumer;
import com.softexpert.desafiobackend.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PicPayPaymentForm {

	@NotNull @NotEmpty @NotBlank
	private String referenceId;
	private Consumer consumer;

	
	public Payment pay(String callbackUrl, String returnUrl, Integer minutesForExpiration) {
		Payment payment = new Payment(minutesForExpiration);

		payment.setReferenceId(referenceId);
		payment.setCallbackUrl(callbackUrl);
		payment.setReturnUrl(returnUrl);

		Consumer consumer = new Consumer();
		consumer.setNome(this.consumer.getNome());
		consumer.setEmail(this.consumer.getEmail());
		consumer.setCelular(this.consumer.getCelular());
		consumer.setValorPagar(this.consumer.getValorPagar());
		consumer.setValorConsumido(this.consumer.getValorConsumido());
		payment.setConsumer(consumer);
		
		return payment;
	}	
}
