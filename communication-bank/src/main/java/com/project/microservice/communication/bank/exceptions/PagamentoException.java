package com.project.microservice.communication.bank.exceptions;

public class PagamentoException extends RuntimeException {

	private static final long serialVersionUID = 593907624216496267L;

	public PagamentoException(String message) {
        super(message);
    }
	
}
