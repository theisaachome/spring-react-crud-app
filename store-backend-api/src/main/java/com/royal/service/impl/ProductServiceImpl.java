package com.royal.service.impl;
import java.io.IOException;
import java.util.List;

import com.royal.payload.FileResponse;
import com.royal.service.FileService;
import org.springframework.stereotype.Service;
import com.royal.entity.Product;
import com.royal.exception.ResourceNotFoundException;
import com.royal.repo.ProductRepo;
import com.royal.service.ProductService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 *@author Isaachome
 */
@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepo repo;
	private FileService fileService;
	public ProductServiceImpl(ProductRepo repo,FileService fileService) {
		this.repo=repo;
		this.fileService = fileService;
	}
	@Override
	public Product createProduct(Product product) {
		return repo.save(product);
	}
	@Override
	public List<Product> getAllProducts() {
		return repo.findAll();
	}

	@Override
	public Product getProductById(Long id) {
		return repo.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Product", "ID", id));
	}

	@Override
	public FileResponse uploadProductImage(long id, String path, MultipartFile image) {
		var p = getProductById(id);
		String filename="";
		try {
			filename = this.fileService.uploadImage(path, image);
			p.setImage(filename);
			p =repo.save(p);
		} catch (IOException e) {
			e.printStackTrace();

		}
		return new FileResponse("Product "+p.getName() +" Image was uploaded", filename);
	}

	@Override
	public Product updateProduct(Long id, Product product) {
		Product old = repo.findById(id).get();
		old.setName(product.getName());
		old.setPrice(product.getPrice());
		old.setStatus(product.isStatus());
		old.setImage(product.getImage());
		
		return repo.save(old);
	}
	
	@Override
	public void deleteProduct(Long id) {
		Product product = repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("", "", id));
		repo.delete(product);
	}
	@Override
	public void deleteByIdIn(List<Long> ids) {
		repo.deleteAllById(ids);
	}

}
