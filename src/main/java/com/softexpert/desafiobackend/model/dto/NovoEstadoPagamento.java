package com.softexpert.desafiobackend.model.dto;

import lombok.Data;
import org.json.JSONObject;

@Data
public class NovoEstadoPagamento {

	private String referenceId;
	private String status;
	
	public NovoEstadoPagamento(String jsonStr) {
		JSONObject json = new JSONObject(jsonStr);
		this.referenceId = json.getString("referenceId");
		this.status = json.getString("status");
	}
}
