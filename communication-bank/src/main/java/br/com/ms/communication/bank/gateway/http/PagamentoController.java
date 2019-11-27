package br.com.ms.communication.bank.gateway.http;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ms.communication.bank.gateway.json.PagamentoJson;
import br.com.ms.communication.bank.gateway.json.RetornoJson;
import br.com.ms.communication.bank.service.PagamentoService;

@RestController
public class PagamentoController {

	@Autowired
	private PagamentoService pagamentoService;
	
	@PostMapping("/pagamento")
	public ResponseEntity<RetornoJson> pagamento(@Valid @NotNull @RequestBody PagamentoJson pagamentoJson) {
		
		pagamentoService.pagamento(pagamentoJson);
		RetornoJson retorno = new RetornoJson();
		retorno.setMensagem("Pagamento Registrado com Sucesso");
		
		return new ResponseEntity<RetornoJson>(retorno, HttpStatus.OK);
	}
}
