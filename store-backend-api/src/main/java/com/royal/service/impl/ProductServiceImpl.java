package com.royal.service.impl;
import java.util.List;
import org.springframework.stereotype.Service;
import com.royal.entity.Product;
import com.royal.exception.ResourceNotFoundException;
import com.royal.repo.ProductRepo;
import com.royal.service.ProductService;

/**
 *
 *@author Isaachome
 */
@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepo repo;
	public ProductServiceImpl(ProductRepo repo) {
		this.repo=repo;
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

}
