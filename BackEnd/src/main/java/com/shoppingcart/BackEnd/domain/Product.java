package com.shoppingcart.BackEnd.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="product")
public class Product {
	
    private int id;
	
	@Id
	private String name;
	private String description;
	private int category_id;
	private int supplier_id;

}
