package com.invoice.demo.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Invoice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String client;
	private Long vatRate;
	@Temporal(TemporalType.DATE)
	private Date invoiceDate;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "invoice_id", referencedColumnName = "id")
	private List<LineItem> lineItem;
	
	@PrePersist
	public void prePersist() {
		invoiceDate = new Date();
	}
	public Invoice() {
		
	}
	public Invoice(Long id, String client, Long vatRate, Date invoiceDate, List<LineItem> lineItem) {
		super();
		this.id = id;
		this.client = client;
		this.vatRate = vatRate;
		this.invoiceDate = invoiceDate;
		this.lineItem = lineItem;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the client
	 */
	public String getClient() {
		return client;
	}
	/**
	 * @param client the client to set
	 */
	public void setClient(String client) {
		this.client = client;
	}
	/**
	 * @return the vatRate
	 */
	public Long getVatRate() {
		return vatRate;
	}
	/**
	 * @param vatRate the vatRate to set
	 */
	public void setVatRate(Long vatRate) {
		this.vatRate = vatRate;
	}
	/**
	 * @return the invoiceDate
	 */
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	/**
	 * @param invoiceDate the invoiceDate to set
	 */
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	/**
	 * @return the lineItem
	 */
	public List<LineItem> getLineItem() {
		return lineItem;
	}
	/**
	 * @param lineItem the lineItem to set
	 */
	public void setLineItem(List<LineItem> lineItem) {
		this.lineItem = lineItem;
	}
	/**
	 * @return the subTotal
	 */
	public BigDecimal getSubTotal() {
		return lineItem.stream().map(i->i.getLineItemTotal()).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2,RoundingMode.HALF_UP);
	}
	/**
	 * @return the subTotal
	 */
	public BigDecimal getVat() {
		return this.getSubTotal().multiply(BigDecimal.valueOf(this.getVatRate())).divide(new BigDecimal(100)).setScale(2,RoundingMode.HALF_UP);
	}
	/**
	 * @return the subTotal
	 */
	public BigDecimal getTotal() {
		return this.getSubTotal().add(this.getVat()).setScale(2,RoundingMode.HALF_UP);
	}
	

}
