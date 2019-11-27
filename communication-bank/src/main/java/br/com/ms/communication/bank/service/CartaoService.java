package br.com.ms.communication.bank.service;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ms.communication.bank.domain.Cartao;
import br.com.ms.communication.bank.gateway.repository.CartaoRepository;

@Service
public class CartaoService {

	@Autowired
	private CartaoRepository cartaoRepository;
	
	public boolean isValido(Integer codigoSegurancaCartao, Integer nroCartao) {
		
		return cartaoRepository.findCartaoValido(codigoSegurancaCartao, nroCartao) > 0;
	}
	
	public boolean isSaldoSuficiente(Integer codigoSegurancaCartao, Integer nroCartao, BigDecimal valorCompra) {
		return cartaoRepository.isSaldoSuficiente(codigoSegurancaCartao, nroCartao, valorCompra) > 0;
	}
	
	public Cartao getCartao(Integer codigoSegurancaCartao, Integer nroCartao) {
		return cartaoRepository.findCartao(codigoSegurancaCartao, nroCartao);
	}
	
	// O @Transactional utilizado para dar o commit na transação
	@Transactional
	public void atualizarSaldo(Integer codigoSegurancaCartao, Integer nroCartao, BigDecimal valorCompra) {
		cartaoRepository.atualizaSaldo(codigoSegurancaCartao, nroCartao, valorCompra);
	}
	
}
