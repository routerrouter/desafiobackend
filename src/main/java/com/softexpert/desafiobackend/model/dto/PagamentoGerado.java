package com.softexpert.desafiobackend.model.dto;

import lombok.Data;
import org.json.JSONObject;

@Data
public class PagamentoGerado {
	
	private String referenceId;
	private String expiresAt;
	private String qrCode;
	private String statusPayment;

	public PagamentoGerado(String jsonStr) {
		JSONObject json = new JSONObject(jsonStr);
		this.referenceId = json.getString("referenceId");
		this.expiresAt = json.getString("expiresAt");
		this.qrCode = json.getJSONObject("qrcode").getString("base64");
		this.statusPayment = "Pagamento Pendente";		
	}
}
