package br.com.ms.communication.bank.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ms.communication.bank.domain.Pagamento;
import br.com.ms.communication.bank.exceptions.PagamentoException;
import br.com.ms.communication.bank.gateway.json.PagamentoJson;
import br.com.ms.communication.bank.gateway.repository.PagamentoRepository;

@Service
public class PagamentoService {

	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private CartaoService cartaoService;
	
	@Transactional
	public void pagamento(PagamentoJson pagamentoJson) {
		
		if(!cartaoService.isValido(pagamentoJson.getCodigoSegurancaCartao(), pagamentoJson.getNroCartao())) {
			throw new PagamentoException("Cartao Invalido");
		}
		
		if(!cartaoService.isSaldoSuficiente(pagamentoJson.getCodigoSegurancaCartao(), pagamentoJson.getNroCartao(), pagamentoJson.getValorCompra())) {
			throw new PagamentoException("Cartao nao possui saldo suficiente");
		}
		
		Pagamento pagamento = new Pagamento();
		pagamento.setValorCompra(pagamentoJson.getValorCompra());
		pagamento.setCartao(cartaoService.getCartao(pagamentoJson.getCodigoSegurancaCartao(), pagamentoJson.getNroCartao()));
		
		pagamentoRepository.save(pagamento);
		
		cartaoService.atualizarSaldo(pagamentoJson.getCodigoSegurancaCartao(), pagamentoJson.getNroCartao(), pagamentoJson.getValorCompra());
		
	}
}
