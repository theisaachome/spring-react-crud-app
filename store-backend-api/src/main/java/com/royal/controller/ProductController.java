package com.royal.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.royal.payload.FileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.royal.entity.Product;
import com.royal.payload.DeleteRecordsDTO;
import com.royal.service.ProductService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 *@author Isaachome
 */
@RestController
@RequestMapping("/api/products")
public class ProductController  {


	@Value("${project.image}")
	private String path;
	@Autowired
	private ProductService service;

	@GetMapping
	public List<Product> getAll() {
		return service.getAllProducts();
	}

	@PostMapping
	public ResponseEntity<Product> create(@RequestBody Product product) {
		System.out.println(product.getName());
		return new ResponseEntity<>(service.createProduct(product),HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> updateById(@RequestBody Product product,@PathVariable(name="id") long id) {
		Product productResponse = service.updateProduct(id, product);
		return new ResponseEntity<>(productResponse,HttpStatus.OK);
	}

	@PostMapping("/{id}/image/upload")
	public ResponseEntity<FileResponse> uploadImageForProduct(
			@PathVariable("id")long id,
			@RequestParam("image") MultipartFile image){
		var fileResponse = service.uploadProductImage(id, path, image);
		return new ResponseEntity<>(fileResponse,HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getById(@PathVariable(name="id")long id) {
		return ResponseEntity.ok(service.getProductById(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable(name="id")long id) {
		service.deleteProduct(id);
		return new ResponseEntity<String>("Product entity deleted successfully",HttpStatus.OK);
	}

	

    @PostMapping("/deleted-records")
    public ResponseEntity<String> delete(@RequestBody DeleteRecordsDTO ids) {
        service.deleteByIdIn(ids.getIds());
        return new ResponseEntity<String>("Product with ids" + ids.getIds() + "were deleted",HttpStatus.OK);
    }

}
