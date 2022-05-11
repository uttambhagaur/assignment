package com.invoice.demo.exceptions;

public class InvoiceNotFoundException extends RuntimeException {

	public InvoiceNotFoundException(Long id){
		super("No invoice find for "+id);
	}
}
