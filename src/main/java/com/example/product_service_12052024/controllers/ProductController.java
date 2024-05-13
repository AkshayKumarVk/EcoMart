package com.example.product_service_12052024.controllers;

import com.example.product_service_12052024.dtos.ProductRequestDto;
import com.example.product_service_12052024.dtos.ProductResponseDto;
import com.example.product_service_12052024.services.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
   
   private ProductService productService;
   
   public ProductController(ProductService productService) {
	  this.productService = productService;
   }
   
   
//   send productId to getSingleProduct methode in the productService interface
   @GetMapping("/products/{id}")
   public ProductResponseDto getProductDetails(@PathVariable("id") int productId) {
	  
	  return productService.getSingleProduct(productId);
	  
   }
   
   @PostMapping("/products")
   public ProductResponseDto createNewProduct(@RequestBody ProductRequestDto productRequestDto) {
	  return productService.addProduct(
			  productRequestDto.getTitle(),
			  productRequestDto.getDescription(),
			  productRequestDto.getImage(),
			  productRequestDto.getCategory(),
			  productRequestDto.getPrice()
	  );
   }
}
