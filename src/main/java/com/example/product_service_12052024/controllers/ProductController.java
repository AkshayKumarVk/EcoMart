package com.example.product_service_12052024.controllers;

import com.example.product_service_12052024.dtos.ProductRequestDto;
import com.example.product_service_12052024.dtos.ProductResponseDto;
import com.example.product_service_12052024.exception.ProductNotFoundException;
import com.example.product_service_12052024.models.Product;
import com.example.product_service_12052024.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

   private final ProductService productService;
   private final ModelMapper modelMapper;

   public ProductController (@Qualifier("fakeStoreProductService") ProductService productService, ModelMapper modelMapper) {
	  this.productService = productService;
	  this.modelMapper = modelMapper;
   }


//      Get All Products
//   @GetMapping()
//   public ResponseEntity<List<ProductResponseDto>> getAllProducts () {
//	  List<Product> products = productService.getAllProducts ();
//
//	  List<ProductResponseDto> productResponseDtos = new ArrayList<> ();
//	  for (Product product : products) {
//		 productResponseDtos.add (convertProductToProductResponseDto (product));
//	  }
//	  return new ResponseEntity<> (productResponseDtos, HttpStatus.OK);
//   }

   //   Get products by page
   @GetMapping()
   public ResponseEntity<List<ProductResponseDto>> getAllProducts (
		   @RequestParam("pageNumber") int pageNumber,
		   @RequestParam("pageSize") int pageSize,
		   @RequestParam("sortBy") String sortParam
   ) throws ProductNotFoundException {
	  Page<Product> products = productService.getAllProducts (
			  pageNumber,
			  pageSize,
			  sortParam);

	  List<ProductResponseDto> productResponseDtos = new ArrayList<> ();

	  for (Product product : products) {
		 productResponseDtos.add (convertProductToProductResponseDto (product));
	  }
	  return ResponseEntity.ok (productResponseDtos);
   }


   //Get Single Product
   //   send productId to getSingleProduct methode in the productService interface
   @GetMapping("{id}")
   public ResponseEntity<ProductResponseDto> getProductDetails (@PathVariable("id") Long productId)
		   throws ProductNotFoundException {

	  Product product = productService.getSingleProduct (productId);
	  return new ResponseEntity<> (convertProductToProductResponseDto (product), HttpStatus.FOUND);

   }


   @PostMapping()
   public ResponseEntity<ProductResponseDto> createNewProduct (@RequestBody ProductRequestDto productRequestDto) {
	  Product product = productService.addProduct (
			  productRequestDto.getTitle (),
			  productRequestDto.getDescription (),
			  productRequestDto.getImageUrl (),
			  productRequestDto.getCategory (),
			  productRequestDto.getPrice ()
	  );

//	  return new ResponseEntity<> (product, HttpStatus.CREATED);
	  ProductResponseDto productResponseDto = convertProductToProductResponseDto (product);

	  return new ResponseEntity<> (productResponseDto, HttpStatus.CREATED);
   }


//   Delete Product

   @DeleteMapping("{id}")
   public ResponseEntity<ProductResponseDto> deleteProduct (@PathVariable("id") Long productId)
		   throws ProductNotFoundException {

	  Product product = productService.deleteProduct (productId);
	  ProductResponseDto productResponseDto = convertProductToProductResponseDto (product);

	  return new ResponseEntity<> (productResponseDto, HttpStatus.ACCEPTED);
   }


   //   Patch Product
   @PatchMapping("{id}")
   public ResponseEntity<ProductResponseDto> updateProduct (@PathVariable("id") Long productId,
															@RequestBody ProductRequestDto productRequestDto)
		   throws ProductNotFoundException {

	  Product product = productService.updateProduct (productId,
			  productRequestDto.getTitle (),
			  productRequestDto.getDescription (),
			  productRequestDto.getImageUrl (),
			  productRequestDto.getCategory (),
			  productRequestDto.getPrice ());

	  ProductResponseDto productResponseDto = convertProductToProductResponseDto (product);

	  return new ResponseEntity<> (productResponseDto, HttpStatus.OK);

   }


   //   Put product
   @PutMapping("{id}")
   public ResponseEntity<ProductResponseDto> replaceProduct (@PathVariable("id") Long productId,
															 @RequestBody ProductRequestDto productRequestDto)
		   throws ProductNotFoundException {

	  Product product = productService.replaceProduct (productId,
			  productRequestDto.getTitle (),
			  productRequestDto.getDescription (),
			  productRequestDto.getImageUrl (),
			  productRequestDto.getCategory (),
			  productRequestDto.getPrice ()
	  );
	  ProductResponseDto productResponseDto = convertProductToProductResponseDto (product);
	  return new ResponseEntity<> (productResponseDto, HttpStatus.OK);


   }

   private ProductResponseDto convertProductToProductResponseDto (Product product) {

	  String categoryTitle = product.getCategory ().getTitle ();

	  ProductResponseDto productResponseDto = modelMapper.map (product, ProductResponseDto.class);
	  productResponseDto.setCategory (categoryTitle);

	  return productResponseDto;

   }

//   //   Add Exception Handler

//   Moved to controllerAdvice

//   @ExceptionHandler(ProductNotFoundException.class)
//   public ResponseEntity<ErrorDto> handleProductNotFoundException (ProductNotFoundException productNotFoundException) {
//
//	  ErrorDto errorDto = new ErrorDto ();
//	  errorDto.setErrorMessage (productNotFoundException.getMessage ());
//
//	  return new ResponseEntity<> (errorDto, HttpStatus.NOT_FOUND);
//   }
}
