package com.example.product_service_12052024.services;

import com.example.product_service_12052024.exception.ProductNotFoundException;
import com.example.product_service_12052024.models.Category;
import com.example.product_service_12052024.models.Product;
import com.example.product_service_12052024.repositories.CategoryRepository;
import com.example.product_service_12052024.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("selfProductService")
public class SelfProductService implements ProductService {

   //   Dependency Injection

   private final ProductRepository productRepository;
   private final CategoryRepository categoryRepository;

   public SelfProductService (ProductRepository productRepository,
							  CategoryRepository categoryRepository) {

	  this.productRepository = productRepository;
	  this.categoryRepository = categoryRepository;
   }

//   Dependency Injection

   @Override
   public Product addProduct (String title, String description,
							  String imageUrl, String category, double price) {

	  Product newProduct = new Product ();
	  newProduct.setTitle (title);
	  newProduct.setDescription (description);
	  newProduct.setPrice (price);
	  newProduct.setImageUrl (imageUrl);

	  Category categoryFromDb = categoryRepository.findByTitle (category);

	  if (categoryFromDb == null) {
		 Category newCategory = new Category ();
		 newCategory.setTitle (category);
		 categoryFromDb = newCategory;
	  }
	  newProduct.setCategory (categoryFromDb);

	  return productRepository.save (newProduct);

   }

   //Get all products
   @Override
   public List<Product> getAllProducts () {
	  return productRepository.findAll ();
   }


//   Page all products
   @Override
   public Page<Product> getAllProducts (int pageNo,
										int pageSize,
										String sortParam) {

	  return productRepository.findAll (PageRequest.of (
			  pageNo,
			  pageSize,
			  Sort.by (sortParam).descending ()));
   }


   @Override
   public Product getSingleProduct (Long id) throws ProductNotFoundException {

	  Product product = productRepository.findByIdIs (id);
	  if (product == null) {
		 throw new ProductNotFoundException ("Product with id " + id + " not found");
	  }

	  return product;
   }


   @Override
   public Product deleteProduct (Long productId) throws ProductNotFoundException {

	  Product product = productRepository.findByIdIs (productId);

	  if (product == null) {
		 throw new ProductNotFoundException ("Product with id " + productId + " not found");
	  }
	  productRepository.delete (product);
	  return product;
   }

   @Override
   public Product updateProduct (Long productId, String title,
								 String description, String imageUrl,
								 String category, double price) throws ProductNotFoundException {

	  Product productInDb = productRepository.findByIdIs (productId);
	  if (productInDb == null) {
		 throw new ProductNotFoundException ("Product with id " + productId + " not found");
	  }
	  if (title != null) {
		 productInDb.setTitle (title);
	  }
	  if (description != null) {
		 productInDb.setDescription (description);
	  }
	  if (imageUrl != null) {
		 productInDb.setImageUrl (imageUrl);
	  }
	  if (price != 0) {
		 productInDb.setPrice (price);
	  }
	  if (category != null) {
		 Category categoryFromDb = categoryRepository.findByTitle (category);
		 if (categoryFromDb == null) {
			Category newCategory = new Category ();
			newCategory.setTitle (category);
			categoryFromDb = newCategory;
		 }
		 productInDb.setCategory (categoryFromDb);
	  }
	  return productRepository.save (productInDb);
   }

   @Override
   public Product replaceProduct (Long productId, String title, String description, String imageUrl, String category, double price) throws ProductNotFoundException {
	  return null;
   }
}
