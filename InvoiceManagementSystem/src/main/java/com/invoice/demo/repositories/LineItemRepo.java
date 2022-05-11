package com.invoice.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.invoice.demo.model.LineItem;

public interface LineItemRepo extends JpaRepository<LineItem, Long> {

}
