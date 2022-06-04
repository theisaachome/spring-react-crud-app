package com.royal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="product_tbl")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="prod_id",nullable = false,updatable = false)
	private long id;
	@Column(name="prod_name",nullable = false)
	private String name;
	@Column(name="prod_price",nullable = false)
	private double price;
	@Column(name="prod_upc",nullable = false)
	private String upc;
	@Column(name="prod_status",nullable = false)
	private boolean status;
	@Column(name="prod_image")
	private String image;
	
	
	
	

}
