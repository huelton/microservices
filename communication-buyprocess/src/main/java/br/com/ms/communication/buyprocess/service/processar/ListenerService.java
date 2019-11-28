package br.com.ms.communication.buyprocess.service.processar;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.com.ms.communication.buyprocess.gateway.json.CompraChaveJson;
import br.com.ms.communication.buyprocess.gateway.json.CompraFinalizadaJson;
import br.com.ms.communication.buyprocess.service.bank.BankService;
import br.com.ms.communication.buyprocess.service.bank.PagamentoRetorno;

@Service
public class ListenerService {

	@Autowired
	private BankService bankService;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Value("fila-compras-aguardando")
	private String nomeFilaRepublicar;
	
	@Value("fila-compras-finalizado")
	private String nomeFilaFinalizado;

	@HystrixCommand(fallbackMethod = "republicOnMessage")
	@RabbitListener(queues = "${fila.entrada}")
	public void onMessage(Message message) throws JsonParseException, JsonMappingException, IOException {
		String json = new String(message.getBody(), "UTF-8");

		System.out.println("Mensagem recebida:" + json);

		ObjectMapper mapper = new ObjectMapper();

		CompraChaveJson compraChaveJson = mapper.readValue(json, CompraChaveJson.class);

		PagamentoRetorno pg = bankService.pagar(compraChaveJson);

		CompraFinalizadaJson compraFinalizadaJson = new CompraFinalizadaJson();
		compraFinalizadaJson.setCompraChaveJson(compraChaveJson);
		compraFinalizadaJson.setPagamentoOK(pg.isPagamentoOK());
		compraFinalizadaJson.setMensagem(pg.getMensagem());

		ObjectMapper obj = new ObjectMapper();
		String jsonFinalizado = obj.writeValueAsString(compraFinalizadaJson);

		rabbitTemplate.convertAndSend(nomeFilaFinalizado, jsonFinalizado);
	}

	public void republicOnMessage(Message message) throws JsonParseException, JsonMappingException, IOException {
		System.out.println("Republicando mensagem...");
		rabbitTemplate.convertAndSend(nomeFilaRepublicar, message);
	}
}
