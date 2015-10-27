package com.ads.in.dao;

import java.math.BigDecimal;

import com.ads.in.model.Product;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;


public class ProductDaoImplTest extends EntityDaoImplTest{

	@Autowired
	ProductDao productDao;

	@Override
	protected IDataSet getDataSet() throws Exception{
		IDataSet dataSet = new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Product.xml"));
		return dataSet;
	}
	
	/* In case you need multiple datasets (mapping different tables) and you do prefer to keep them in separate XML's
	@Override
	protected IDataSet getDataSet() throws Exception {
	  IDataSet[] datasets = new IDataSet[] {
			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Product.xml")),
			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Benefits.xml")),
			  new FlatXmlDataSet(this.getClass().getClassLoader().getResourceAsStream("Departements.xml"))
	  };
	  return new CompositeDataSet(datasets);
	}
	*/

	@Test
	public void findById(){
		Assert.assertNotNull(productDao.findById(1));
		Assert.assertNull(productDao.findById(3));
	}

	
	@Test
	public void saveProduct(){
		productDao.saveProduct(getSampleProduct());
		Assert.assertEquals(productDao.findAllProducts().size(), 3);
	}
	
	@Test
	public void deleteProductBySsn(){
		productDao.deleteProductBySsn("11111");
		Assert.assertEquals(productDao.findAllProducts().size(), 1);
	}
	
	@Test
	public void deleteProductByInvalidSsn(){
		productDao.deleteProductBySsn("23423");
		Assert.assertEquals(productDao.findAllProducts().size(), 2);
	}

	@Test
	public void findAllProducts(){
		Assert.assertEquals(productDao.findAllProducts().size(), 2);
	}
	
	@Test
	public void findProductBySsn(){
		Assert.assertNotNull(productDao.findProductBySsn("11111"));
		Assert.assertNull(productDao.findProductBySsn("14545"));
	}

	public Product getSampleProduct(){
		Product product = new Product();
		product.setName("Karen");
		product.setSsn("12345");
		product.setSalary(new BigDecimal(10980));
		product.setJoiningDate(new LocalDate());
		return product;
	}

}
