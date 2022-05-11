package com.invoice.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.invoice.demo.exceptions.InvoiceNotFoundException;
import com.invoice.demo.model.Invoice;
import com.invoice.demo.repositories.InvoiceRepo;
import com.invoice.demo.repositories.LineItemRepo;

@RestController
public class InvoiceController {
	
	@Autowired
	private InvoiceRepo invoiceRepo;
	
	
	
	@PostMapping("/invoices")
	public Invoice addInvoice(@RequestBody Invoice invoice) {
		
		return invoiceRepo.save(invoice);
	}
	
	@GetMapping("/invoices")
	public List<Invoice> viewAllInvoices() {
		
		return invoiceRepo.findAll();
	}
	
	@GetMapping("/invoices/{invoiceId}")
	public Invoice viewInvoice(@PathVariable Long invoiceId) {
		
		return invoiceRepo.findById(invoiceId).orElseThrow(()-> new InvoiceNotFoundException(invoiceId));
	}

}
