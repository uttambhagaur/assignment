package com.invoice.demo.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LineItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long quantity;
	private String description;
	private BigDecimal unitPrice;
	
	public LineItem() {
		
	}
	
	public LineItem(Long id, Long quantity, String description, BigDecimal unitPrice) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.description = description;
		this.unitPrice = unitPrice;
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
	 * @return the quantity
	 */
	public Long getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the unitPrice
	 */
	public BigDecimal getUnitPrice() {
		return unitPrice.setScale(2,RoundingMode.HALF_UP);
	}
	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	

	/**
	 * @return the unitPrice
	 */
	public BigDecimal getLineItemTotal() {
		return getUnitPrice().multiply(BigDecimal.valueOf(this.getQuantity())).setScale(2,RoundingMode.HALF_UP);
	}
	
	

}
