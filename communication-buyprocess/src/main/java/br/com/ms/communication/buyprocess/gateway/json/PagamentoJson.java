package br.com.ms.communication.buyprocess.gateway.json;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoJson {

	private Integer nroCartao;
	private Integer codigoSegurancaCartao;
	private BigDecimal valorCompra;

}
