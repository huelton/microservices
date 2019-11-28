package br.com.ms.communication.buyfeedback.domain;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RedisHash("compra")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompraRedis {

	@Id
	private String id;
	private String mensagem;
	
	private Integer codigoPassagem;
	private Integer nroCartao;
	private BigDecimal valorPassagem;
	
	private boolean pagamentoOK;

	
	
}