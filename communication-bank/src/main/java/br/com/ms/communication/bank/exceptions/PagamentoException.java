package br.com.ms.communication.bank.exceptions;

public class PagamentoException extends RuntimeException {

	private static final long serialVersionUID = -7556602635532964297L;

	public PagamentoException(String message) {
		super(message);
	}
}
