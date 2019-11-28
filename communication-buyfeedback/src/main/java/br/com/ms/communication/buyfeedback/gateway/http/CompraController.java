package br.com.ms.communication.buyfeedback.gateway.http;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ms.communication.buyfeedback.domain.CompraRedis;
import br.com.ms.communication.buyfeedback.exceptions.NaoFinalizadoException;
import br.com.ms.communication.buyfeedback.gateway.repository.CompraRedisRepository;

@RestController
public class CompraController {

	@Autowired
	private CompraRedisRepository compraRedisRepository;
	
	@RequestMapping(path = "/{chave}", method = RequestMethod.GET)
	public CompraRedis status(@PathVariable("chave") String chave){
		
		Optional<CompraRedis> compra = compraRedisRepository.findById(chave);
		
		if( !compra.isPresent()){
			throw new NaoFinalizadoException();
		}
		
		return compra.get();
	}
	
	@RequestMapping(path = "/meunome", method = RequestMethod.GET)
	public String status(){
		return "Estou na máquina do: Huelton";
	}
}
