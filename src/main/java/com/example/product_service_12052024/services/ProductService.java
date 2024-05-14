package com.example.product_service_12052024.services;

import com.example.product_service_12052024.dtos.ProductResponseDto;

import java.util.List;

public interface ProductService {

//   routed to the implementation

   public List<ProductResponseDto> getAllProducts();

   public ProductResponseDto getSingleProduct(int id);
   
   public ProductResponseDto addProduct(
           String title,
           String description,
           String imageUrl,
           String category,
           double price
   );
   
}
