package exceptions;

import java.util.Date;

import control.Hospital;

public class EmailInvalidException extends RuntimeException{

	public EmailInvalidException() {
		super("Email ivalid please use example@example.com format");
	}

}
