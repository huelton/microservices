package br.com.ms.communication.bank.gateway.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import br.com.ms.communication.bank.domain.Cartao;

public interface CartaoRepository extends Repository<Cartao, Long>{

	@Query("SELECT COUNT(obj.id) FROM Cartao obj WHERE obj.codigoSegurancaCartao = ?1 AND obj.nroCartao = ?2")
	Integer findCartaoValido(Integer codigoSegurancaCartao, Integer nroCartao);
	
	@Query("SELECT COUNT(obj.id) FROM Cartao obj WHERE obj.codigoSegurancaCartao = ?1 AND obj.nroCartao = ?2 AND obj.valorCredito >= ?3")
	Integer isSaldoSuficiente(Integer codigoSegurancaCartao, Integer nroCartao, BigDecimal valorCompra);
	
	@Query("FROM Cartao obj WHERE obj.codigoSegurancaCartao = ?1 AND obj.nroCartao = ?2")
	Cartao findCartao(Integer codigoSegurancaCartao, Integer nroCartao);
	
	@Modifying
	@Query("UPDATE Cartao SET valorCredito = (valorCredito - ?3) WHERE codigoSegurancaCartao = ?1 AND nroCartao = ?2")
	void atualizaSaldo(Integer codigoSegurancaCartao, Integer nroCartao, BigDecimal valorCompra);
}
