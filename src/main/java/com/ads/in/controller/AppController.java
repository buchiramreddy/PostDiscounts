package com.ads.in.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import com.ads.in.model.Product;
import com.ads.in.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	ProductService service;
	
	@Autowired
	MessageSource messageSource;

	/*
	 * This method will list all existing employees.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listProducts(ModelMap model) {

		List<Product> products = service.findAllProducts();
		model.addAttribute("products", products);
		return "allproducts";
	}

	/*
	 * This method will provide the medium to add a new employee.
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.GET)
	public String newProduct(ModelMap model) {
		Product product = new Product();
		model.addAttribute("product", product);
		model.addAttribute("edit", false);
		return "postproduct";
	}

	/*
	 * This method will be called on form submission, handling POST request for
	 * saving product in database. It also validates the user input
	 */
	@RequestMapping(value = { "/new" }, method = RequestMethod.POST)
	public String saveProduct(@Valid Product product, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			return "postproduct";
		}

		/*
		 * Preferred way to achieve uniqueness of field [ssn] should be implementing custom @Unique annotation 
		 * and applying it on field [ssn] of Model class [Product].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!service.isProductSsnUnique(product.getId(), product.getSsn())){
			FieldError ssnError =new FieldError("product","ssn",messageSource.getMessage("non.unique.ssn", new String[]{product.getSsn()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "postproduct";
		}
		
		service.saveProduct(product);

		model.addAttribute("success", "Product " + product.getName() + " posted successfully");
		return "success";
	}


	/*
	 * This method will provide the medium to update an existing employee.
	 */
	@RequestMapping(value = { "/edit-{ssn}-product" }, method = RequestMethod.GET)
	public String editProduct(@PathVariable String ssn, ModelMap model) {
		Product product = service.findProductBySsn(ssn);
		model.addAttribute("product", product);
		model.addAttribute("edit", true);
		return "postproduct";
	}
	
	/*
	 * This method will be called on form submission, handling POST request for
	 * updating product in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-{ssn}-product" }, method = RequestMethod.POST)
	public String updateProduct(@Valid Product product, BindingResult result,
			ModelMap model, @PathVariable String ssn) {

		if (result.hasErrors()) {
			return "postproduct";
		}

		if(!service.isProductSsnUnique(product.getId(), product.getSsn())){
			FieldError ssnError =new FieldError("product","ssn",messageSource.getMessage("non.unique.ssn", new String[]{product.getSsn()}, Locale.getDefault()));
		    result.addError(ssnError);
			return "postproduct";
		}

		service.updateProduct(product);

		model.addAttribute("success", "Product " + product.getName()	+ " updated successfully");
		return "success";
	}

	
	/*
	 * This method will delete an employee by it's SSN value.
	 */
	@RequestMapping(value = { "/delete-{ssn}-product" }, method = RequestMethod.GET)
	public String deleteProduct(@PathVariable String ssn) {
		service.deleteProductBySsn(ssn);
		return "redirect:/list";
	}

}
