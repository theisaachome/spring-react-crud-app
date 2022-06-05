package com.royal.service.impl;
import java.util.List;
import org.springframework.stereotype.Service;
import com.royal.entity.Product;
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
	public Product updateProductById(Long id, Product product) {
		Product old = repo.findById(id).get();
		old.setName(product.getName());
		old.setPrice(product.getPrice());
		old.setStatus(product.isStatus());
		old.setImage(product.getImage());
		
		return repo.save(old);
	}
	@Override
	public void deleteProductById(Long id) {
		Product product = repo.findById(id).get();
		repo.delete(product);
	}
	@Override
	public Product getProductById(Long id) {
		Product product = repo.findById(id).get();
		
		return product;
	}

}
