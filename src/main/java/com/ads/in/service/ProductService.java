package com.ads.in.service;

import java.util.List;

import com.ads.in.model.Product;

public interface ProductService {

	Product findById(int id);
	
	void saveProduct(Product product);
	
	void updateProduct(Product product);
	
	void deleteProductBySsn(String ssn);

	List<Product> findAllProducts();
	
	Product findProductBySsn(String ssn);

	boolean isProductSsnUnique(Integer id, String ssn);
	
}
