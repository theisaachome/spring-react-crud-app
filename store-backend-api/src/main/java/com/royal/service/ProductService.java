package com.royal.service;
/**
 *
 *@author Isaachome
 */

import java.util.List;

import com.royal.entity.Product;

public interface ProductService {

	List<Product> getAllProducts();
	Product createProduct(Product product);
	Product getProductById(Long id);
	Product updateProductById(Long id,Product product);
	void deleteProductById(Long id);
	
}
