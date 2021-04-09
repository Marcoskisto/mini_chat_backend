package br.gov.sp.fatec.mini_chat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class RegistroNaoEncontradoException extends RuntimeException{

	private static final long serialVersionUID = 5779078222484191355L;

	public RegistroNaoEncontradoException() {
		super();
	}
	
	public RegistroNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public RegistroNaoEncontradoException(Throwable cause) {
		super(cause);
	}
	
	public RegistroNaoEncontradoException(String mensagem, Throwable cause) {
		super(mensagem, cause);
	}
}
