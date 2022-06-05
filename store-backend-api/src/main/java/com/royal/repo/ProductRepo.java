package com.royal.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.royal.entity.Product;

/**
*
*@author Isaachome
*/
public interface ProductRepo extends JpaRepository<Product, Long> {

}
