package com.project.microservice.communication.bank.gateway.http;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.microservice.communication.bank.gateway.json.PagamentoJson;
import com.project.microservice.communication.bank.gateway.json.RetornoJson;
import com.project.microservice.communication.bank.service.PagamentoService;

@RestController
public class PagamentoController {

	@Autowired
	private PagamentoService pagamentoService;
	
	@RequestMapping(path = "/pagamento", method = RequestMethod.POST)
	public ResponseEntity<RetornoJson> pagamento(
			@Valid @NotNull @RequestBody PagamentoJson pagamentoJson) {

		pagamentoService.pagamento(pagamentoJson);
		
		RetornoJson retorno = new RetornoJson();
		retorno.setMensagem("Pagamento registrado com sucesso");
		
		return new ResponseEntity<RetornoJson>(retorno, HttpStatus.OK);
	}
}

