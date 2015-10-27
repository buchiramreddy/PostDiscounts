package com.ads.in.dao;

import java.util.List;

import com.ads.in.model.Product;

public interface ProductDao {

	Product findById(int id);

	void saveProduct(Product product);
	
	void deleteProductBySsn(String ssn);
	
	List<Product> findAllProducts();

	Product findProductBySsn(String ssn);

}
