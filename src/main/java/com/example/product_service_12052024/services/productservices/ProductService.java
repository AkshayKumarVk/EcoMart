package com.example.product_service_12052024.services.productservices;

import com.example.product_service_12052024.exception.ProductNotFoundException;
import com.example.product_service_12052024.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

//   routed to the implementation


   //   Get All Products
   public List<Product> getAllProducts ();

//   Get all products by page
   public Page<Product> getAllProducts ( int pageNo, int pageSize,
										 String sortParam);

   //   Get Single Product
   Product getSingleProduct (Long id) throws ProductNotFoundException;


   //   Add Products
   Product addProduct (

		   String title,
		   String description,
		   String imageUrl,
		   String category,
		   double price
   );

   //   Delete Product
   Product deleteProduct (Long productId) throws ProductNotFoundException;

   //   Patch Product
   Product updateProduct (
		   Long productId,
		   String title,
		   String description,
		   String imageUrl,
		   String category,
		   double price
   ) throws ProductNotFoundException;


   //   Put Mapping
   Product replaceProduct (
		   Long productId,
		   String title,
		   String description,
		   String imageUrl,
		   String category,
		   double price) throws ProductNotFoundException;
}
