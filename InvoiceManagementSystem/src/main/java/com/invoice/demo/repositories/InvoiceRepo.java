package com.invoice.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invoice.demo.model.Invoice;

public interface InvoiceRepo extends JpaRepository<Invoice, Long> {

}
