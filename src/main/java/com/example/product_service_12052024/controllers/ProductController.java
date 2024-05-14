package com.example.product_service_12052024.controllers;

import com.example.product_service_12052024.dtos.ProductRequestDto;
import com.example.product_service_12052024.dtos.ProductResponseDto;
import com.example.product_service_12052024.models.Product;
import com.example.product_service_12052024.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

   private final ProductService productService;
   private final ModelMapper modelMapper;

   public ProductController (ProductService productService, ModelMapper modelMapper) {
	  this.productService = productService;
	  this.modelMapper = modelMapper;
   }

   @GetMapping("/products")
   public List<Product> getAllProducts () {

	  List<Product> products = productService.getAllProducts ();

	  return products;
   }


   //   send productId to getSingleProduct methode in the productService interface
   @GetMapping("/products/{id}")
   public ProductResponseDto getProductDetails (@PathVariable("id") int productId) {

	  Product product = productService.getSingleProduct (productId);
	  return convertProductToProductResponseDto (product);

   }


   @PostMapping("/products")
   public Product createNewProduct (@RequestBody ProductRequestDto productRequestDto) {
	  return productService.addProduct (
			  productRequestDto.getTitle (),
			  productRequestDto.getDescription (),
			  productRequestDto.getImage (),
			  productRequestDto.getCategory (),
			  productRequestDto.getPrice ()
	  );
   }


   private ProductResponseDto convertProductToProductResponseDto (Product product) {

	  String categoryTitle= product.getCategory ().getTitle ();

	  ProductResponseDto productResponseDto = modelMapper.map (product, ProductResponseDto.class);
	  productResponseDto.setCategory (categoryTitle);

	  return productResponseDto;

   }
}
