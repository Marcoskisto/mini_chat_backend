package br.gov.sp.fatec.mini_chat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ErroAoSalvarRegistroException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ErroAoSalvarRegistroException() {
		super();
	}
	
	public ErroAoSalvarRegistroException(String mensagem) {
		super(mensagem);
	}
	
	public ErroAoSalvarRegistroException(Throwable cause) {
		super(cause);
	}
	
	public ErroAoSalvarRegistroException(String mensagem, Throwable cause) {
		super(mensagem, cause);
	}
}
