package com.royal.service;

/**
 *
 *@author Isaachome
 */

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.royal.entity.Product;
import com.royal.payload.FileResponse;

import org.springframework.web.multipart.MultipartFile;

public interface ProductService {

	List<Product> getAllProducts();

	Product createProduct(Product product);

	Product getProductById(Long id);

	Product updateProduct(Long id, Product product);

	void deleteProduct(Long id);

	@Modifying
	@Transactional
	@Query("delete from Product where u.id in(:ids)")
	void deleteByIdIn(List<Long> ids);

	FileResponse uploadProductImage(long id, String path, MultipartFile image);

}
