package com.ads.in.dao;

import java.util.List;

import com.ads.in.model.Product;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository("productDao")
public class ProductDaoImpl extends AbstractDao<Integer, Product> implements ProductDao {

	public Product findById(int id) {
		return getByKey(id);
	}

	public void saveProduct(Product product) {
		persist(product);
	}

	public void deleteProductBySsn(String ssn) {
		Query query = getSession().createSQLQuery("delete from Product where ssn = :ssn");
		query.setString("ssn", ssn);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Product> findAllProducts() {
		Criteria criteria = createEntityCriteria();
		return (List<Product>) criteria.list();
	}

	public Product findProductBySsn(String ssn) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("ssn", ssn));
		return (Product) criteria.uniqueResult();
	}
}
