package com.example.product_service_12052024.services;

import com.example.product_service_12052024.dtos.ProductResponseDto;
import com.example.product_service_12052024.models.Product;

import java.util.List;

public interface ProductService {

//   routed to the implementation

   public List<Product> getAllProducts();

   public Product getSingleProduct(int id);
   
   public Product addProduct(
		   
           String title,
           String description,
           String imageUrl,
           String category,
           double price
   );
   
}
