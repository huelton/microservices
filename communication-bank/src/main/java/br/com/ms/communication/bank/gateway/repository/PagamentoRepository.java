package br.com.ms.communication.bank.gateway.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.ms.communication.bank.domain.Pagamento;

public interface PagamentoRepository extends CrudRepository<Pagamento, Long>{

}
