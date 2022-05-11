package com.invoice.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.invoice.demo.controller.InvoiceController;
import com.invoice.demo.model.Invoice;
import com.invoice.demo.model.LineItem;
import com.invoice.demo.repositories.InvoiceRepo;
import static org.hamcrest.Matchers.*;

@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper mapper;
	
	@MockBean
	InvoiceRepo invoiceRepo;
	List<LineItem> lineItemList1 = new ArrayList<>(java.util.Arrays.asList(
			new LineItem(1l, 10l,"lineItem description 1", new BigDecimal(1200.237)),
			new LineItem(2l, 11l,"lineItem description 2", new BigDecimal(1600.234)), 
			new LineItem(3l, 13l,"lineItem description 3", new BigDecimal(1500.232))
			));
	Invoice invoice1 = new Invoice(1l,"client 1",10l,new Date(),lineItemList1);
	
	@Test
	public void addInvoice_success() throws Exception{
		
		Mockito.when(invoiceRepo.save(invoice1)).thenReturn(invoice1);
		
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/invoices")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(invoice1));
		mockMvc.perform(mockRequest)
				.andExpect(status().isOk())
				;
	}
	
	@Test
	public void viewAllInvoices_success() throws Exception{
		List<Invoice> invoices = new ArrayList<>(java.util.Arrays.asList(invoice1));
		
		Mockito.when(invoiceRepo.findAll()).thenReturn(invoices);
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/invoices")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$",hasSize(1)))
				.andExpect(jsonPath("$[0].client", is("client 1")))
				;
	}
	
	@Test
	public void viewInvoice_success() throws Exception{
		
		Mockito.when(invoiceRepo.findById(invoice1.getId())).thenReturn(java.util.Optional.of( invoice1));
		
		mockMvc.perform(MockMvcRequestBuilders
				.get("/invoices/1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$",notNullValue()))
				.andExpect(jsonPath("$.client", is("client 1")))
				;
	}
	
	

}
