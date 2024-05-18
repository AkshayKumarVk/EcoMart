package com.example.product_service_12052024.services;

import com.example.product_service_12052024.dtos.ProductResponseDto;
import com.example.product_service_12052024.exception.ProductNotFoundException;
import com.example.product_service_12052024.models.Product;

import java.util.List;

public interface ProductService {

//   routed to the implementation


//   Get All Products
   public List<Product> getAllProducts();

//   Get Single Product
   public Product getSingleProduct(Long id) throws ProductNotFoundException;


//   Add Products
   public Product addProduct(

           String title,
           String description,
           String imageUrl,
           String category,
           double price
   );
//   Delete Product
   public  Product deleteProduct( Long productId) throws ProductNotFoundException;

}
