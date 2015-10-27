package com.ads.in.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.ads.in.model.Product;
import com.ads.in.service.ProductService;
import org.joda.time.LocalDate;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import static org.mockito.Mockito.atLeastOnce;

import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AppControllerTest {

	@Mock
	ProductService service;
	
	@Mock
	MessageSource message;
	
	@InjectMocks
	AppController appController;
	
	@Spy
	List<Product> products = new ArrayList<Product>();

	@Spy
	ModelMap model;
	
	@Mock
	BindingResult result;
	
	@BeforeClass
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		products = getProductList();
	}
	
	@Test
	public void listProducts(){
		when(service.findAllProducts()).thenReturn(products);
		Assert.assertEquals(appController.listProducts(model), "allproducts");
		Assert.assertEquals(model.get("products"), products);
		verify(service, atLeastOnce()).findAllProducts();
	}
	
	@Test
	public void newProduct(){
		Assert.assertEquals(appController.newProduct(model), "postproduct");
		Assert.assertNotNull(model.get("product"));
		Assert.assertFalse((Boolean)model.get("edit"));
		Assert.assertEquals(((Product)model.get("product")).getId(), 0);
	}


	@Test
	public void saveProductWithValidationError(){
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(service).saveProduct(any(Product.class));
		Assert.assertEquals(appController.saveProduct(products.get(0), result, model), "postproduct");
	}

	@Test
	public void saveProductWithValidationErrorNonUniqueSSN(){
		when(result.hasErrors()).thenReturn(false);
		when(service.isProductSsnUnique(anyInt(), anyString())).thenReturn(false);
		Assert.assertEquals(appController.saveProduct(products.get(0), result, model), "postproduct");
	}

	
	@Test
	public void saveProductWithSuccess(){
		when(result.hasErrors()).thenReturn(false);
		when(service.isProductSsnUnique(anyInt(), anyString())).thenReturn(true);
		doNothing().when(service).saveProduct(any(Product.class));
		Assert.assertEquals(appController.saveProduct(products.get(0), result, model), "success");
		Assert.assertEquals(model.get("success"), "Product Axel posted successfully");
	}

	@Test
	public void editProduct(){
		Product emp = products.get(0);
		when(service.findProductBySsn(anyString())).thenReturn(emp);
		Assert.assertEquals(appController.editProduct(anyString(), model), "postproduct");
		Assert.assertNotNull(model.get("product"));
		Assert.assertTrue((Boolean)model.get("edit"));
		Assert.assertEquals(((Product)model.get("product")).getId(), 1);
	}

	@Test
	public void updateProductWithValidationError(){
		when(result.hasErrors()).thenReturn(true);
		doNothing().when(service).updateProduct(any(Product.class));
		Assert.assertEquals(appController.updateProduct(products.get(0), result, model, ""), "postproduct");
	}

	@Test
	public void updateProductWithValidationErrorNonUniqueSSN(){
		when(result.hasErrors()).thenReturn(false);
		when(service.isProductSsnUnique(anyInt(), anyString())).thenReturn(false);
		Assert.assertEquals(appController.updateProduct(products.get(0), result, model, ""), "postproduct");
	}

	@Test
	public void updateProductWithSuccess(){
		when(result.hasErrors()).thenReturn(false);
		when(service.isProductSsnUnique(anyInt(), anyString())).thenReturn(true);
		doNothing().when(service).updateProduct(any(Product.class));
		Assert.assertEquals(appController.updateProduct(products.get(0), result, model, ""), "success");
		Assert.assertEquals(model.get("success"), "Product Axel updated successfully");
	}
	
	
	@Test
	public void deleteProduct(){
		doNothing().when(service).deleteProductBySsn(anyString());
		Assert.assertEquals(appController.deleteProduct("123"), "redirect:/list");
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
