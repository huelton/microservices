package br.com.ms.communication.bank.gateway.json;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoJson {

	@NotNull(message="Numero do cartao e obrigatorio")
	private Integer nroCartao;
	
	@NotNull(message="Codigo de segurança do cartao e obrigatorio")
	private Integer codigoSegurancaCartao;
	
	@NotNull(message="Valor de Compra e obrigatorio")
	private BigDecimal valorCompra;
	
	
}
