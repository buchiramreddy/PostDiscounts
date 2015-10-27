package com.ads.in.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

import com.ads.in.model.Product;
import org.joda.time.LocalDate;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ads.in.dao.ProductDao;

public class ProductServiceImplTest {

	@Mock
	ProductDao dao;
	
	@InjectMocks
	ProductServiceImpl employeeService;
	
	@Spy
	List<Product> products = new ArrayList<Product>();
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		products = getProductList();
	}

	@Test
	public void findById(){
		Product emp = products.get(0);
		when(dao.findById(anyInt())).thenReturn(emp);
		Assert.assertEquals(employeeService.findById(emp.getId()),emp);
	}

	@Test
	public void saveProduct(){
		doNothing().when(dao).saveProduct(any(Product.class));
		employeeService.saveProduct(any(Product.class));
		verify(dao, atLeastOnce()).saveProduct(any(Product.class));
	}
	
	@Test
	public void updateProduct(){
		Product emp = products.get(0);
		when(dao.findById(anyInt())).thenReturn(emp);
		employeeService.updateProduct(emp);
		verify(dao, atLeastOnce()).findById(anyInt());
	}

	@Test
	public void deleteProductBySsn(){
		doNothing().when(dao).deleteProductBySsn(anyString());
		employeeService.deleteProductBySsn(anyString());
		verify(dao, atLeastOnce()).deleteProductBySsn(anyString());
	}
	
	@Test
	public void findAllProducts(){
		when(dao.findAllProducts()).thenReturn(products);
		Assert.assertEquals(employeeService.findAllProducts(), products);
	}
	
	@Test
	public void findProductBySsn(){
		Product emp = products.get(0);
		when(dao.findProductBySsn(anyString())).thenReturn(emp);
		Assert.assertEquals(employeeService.findProductBySsn(anyString()), emp);
	}

	@Test
	public void isProductSsnUnique(){
		Product emp = products.get(0);
		when(dao.findProductBySsn(anyString())).thenReturn(emp);
		Assert.assertEquals(employeeService.isProductSsnUnique(emp.getId(), emp.getSsn()), true);
	}
	
	
	public List<Product> getProductList(){
		Product e1 = new Product();
		e1.setId(1);
		e1.setName("Axel");
		e1.setJoiningDate(new LocalDate());
		e1.setSalary(new BigDecimal(10000));
		e1.setSsn("XXX111");
		
		Product e2 = new Product();
		e2.setId(2);
		e2.setName("Jeremy");
		e2.setJoiningDate(new LocalDate());
		e2.setSalary(new BigDecimal(20000));
		e2.setSsn("XXX222");
		
		products.add(e1);
		products.add(e2);
		return products;
	}
	
}
