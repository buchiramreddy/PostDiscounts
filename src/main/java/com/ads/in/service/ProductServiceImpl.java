package com.ads.in.service;

import java.util.List;

import com.ads.in.dao.ProductDao;
import com.ads.in.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao dao;
	
	public Product findById(int id) {
		return dao.findById(id);
	}

	public void saveProduct(Product product) {
		dao.saveProduct(product);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateProduct(Product product) {
		Product entity = dao.findById(product.getId());
		if(entity!=null){
			entity.setName(product.getName());
			entity.setJoiningDate(product.getJoiningDate());
			entity.setSalary(product.getSalary());
			entity.setSsn(product.getSsn());
		}
	}

	public void deleteProductBySsn(String ssn) {
		dao.deleteProductBySsn(ssn);
	}
	
	public List<Product> findAllProducts() {
		return dao.findAllProducts();
	}

	public Product findProductBySsn(String ssn) {
		return dao.findProductBySsn(ssn);
	}

	public boolean isProductSsnUnique(Integer id, String ssn) {
		Product product = findProductBySsn(ssn);
		return ( product == null || ((id != null) && (product.getId() == id)));
	}
	
}
