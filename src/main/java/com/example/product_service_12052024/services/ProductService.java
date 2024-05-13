package com.example.product_service_12052024.services;

import com.example.product_service_12052024.dtos.ProductResponseDto;

public interface ProductService {
   
   public ProductResponseDto getSingleProduct(int id);
   
   public ProductResponseDto addProduct(
           String title,
           String description,
           String imageUrl,
           String category,
           double price
   );
   
}
