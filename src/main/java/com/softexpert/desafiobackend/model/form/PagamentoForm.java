package com.softexpert.desafiobackend.model.form;


import com.softexpert.desafiobackend.model.Consumidor;
import com.softexpert.desafiobackend.model.Pagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoForm {

	@NotNull @NotEmpty @NotBlank
	private String referenceId;
	@NotNull 
	private BigDecimal valorPagar;
	@NotNull @NotEmpty @NotBlank
	private String nome;
	@NotNull @NotEmpty @NotBlank
	private String email;
	@NotNull @NotEmpty @NotBlank
	private String celular;
	
	public Pagamento pagamento(String callbackUrl, String returnUrl, Integer minutesForExpiration) {
		Pagamento pagamento = new Pagamento(minutesForExpiration);

		pagamento.setReferenceId(referenceId);
		pagamento.setCallbackUrl(callbackUrl);
		pagamento.setReturnUrl(returnUrl);
		//pagamento.setValorPagar(valorPagar);
		
		Consumidor consumidor = new Consumidor();
		consumidor.setNome(nome);
		consumidor.setEmail(email);
		consumidor.setCelular(celular);
		pagamento.setConsumidor(consumidor);
		
		return pagamento;
	}	
}
