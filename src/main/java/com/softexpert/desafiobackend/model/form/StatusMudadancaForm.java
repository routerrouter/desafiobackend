package com.softexpert.desafiobackend.model.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusMudadancaForm {

	private String referenceId;
	private String authorizationId;
}
