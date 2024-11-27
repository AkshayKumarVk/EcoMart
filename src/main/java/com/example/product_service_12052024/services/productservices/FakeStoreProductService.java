package com.example.product_service_12052024.services.productservices;

import com.example.product_service_12052024.dtos.FakeStoreDto;
import com.example.product_service_12052024.exception.ProductNotFoundException;
import com.example.product_service_12052024.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductService implements  ProductService {

   private final RestTemplate restTemplate;
   private RedisTemplate redisTemplate;

   public FakeStoreProductService (RestTemplate restTemplate,
								   RedisTemplate redisTemplate) {
	  this.restTemplate = restTemplate;
	  this.redisTemplate = redisTemplate;

   }

   //   route from ProductService interface
   @Override
   public List<Product> getAllProducts () {
	  FakeStoreDto[] fakeStoreDtoList = restTemplate.getForObject (
			  "https://fakestoreapi.com/products/",
			  FakeStoreDto[].class
	  );

	  List<Product> products = new ArrayList<> ();

	  assert fakeStoreDtoList != null;
	  for (FakeStoreDto fakeStoreDto : fakeStoreDtoList) {
		 products.add (fakeStoreDto.toProduct ());
	  }
	  return products;
   }

//   Get Products with page
   @Override
   public Page<Product> getAllProducts (int pageNo, int pageSize, String sortParam) {
	  return null;
   }


   @Override
   public Product getSingleProduct (Long productId) throws ProductNotFoundException {

//	  Caching
//	  First checks in cache

	  Product productInCache =(Product) redisTemplate.opsForHash ().get (
			  "PRODUCT",
			  "PRODUCT_"+productId);
	  
	  if (productInCache != null) {
		 System.out.println ("Product: "+productId+" found in cache");
		 return productInCache;
	  }

//	  Caching


	  System.out.println ("Product: "+productId+" not in cache");
//	  send to fakeStore dto
	  FakeStoreDto fakeStoreDto = restTemplate.getForObject (
			  "https://fakestoreapi.com/products/" + productId,
			  FakeStoreDto.class    //get response back and converted to FakeStoreDto
	  );

	  if (fakeStoreDto == null) {

		 throw new ProductNotFoundException (
				 "Product with id " + productId + " Not available, try something different."
		 );
	  }
//	  convert to response to controller
	  Product productNotInCache = fakeStoreDto.toProduct ();
//	  convert to response to controller

//	  Adding to cache
	  redisTemplate.opsForHash ().put ("PRODUCT",
			  "PRODUCT_"+productId, productNotInCache);
//	  Adding to cache

	  return  productNotInCache;
   }

   //Add product
   @Override
   public Product addProduct (
		   String title,
		   String description,
		   String imageUrl,
		   String category,
		   double price
   ) {
	  FakeStoreDto requestDto = new FakeStoreDto ();
	  requestDto.setTitle (title);
	  requestDto.setDescription (description);
	  requestDto.setImage (imageUrl);
	  requestDto.setCategory (category);
	  requestDto.setPrice (price);

	  FakeStoreDto response = restTemplate.postForObject (
			  "https://fakestoreapi.com/products/",
			  requestDto,
			  FakeStoreDto.class
	  );
//	  sending to Fake store class object.
	  assert response != null;
	  return response.toProduct ();


   }

   //   Delete Product
   @Override
   public Product deleteProduct (Long productId) throws ProductNotFoundException {
	  FakeStoreDto fakeStoreDto = restTemplate.exchange (
			  "https://fakestoreapi.com/products/" + productId,
			  HttpMethod.DELETE, null, FakeStoreDto.class
	  ).getBody ();

	  if (fakeStoreDto == null) {
		 throw new ProductNotFoundException (
				 "Product with id " + productId + "not found, try deleting a product with id less than 21"
		 );
	  }

	  return fakeStoreDto.toProduct ();
   }


   //   Patch Product
   @Override
   public Product updateProduct (
		   Long productId,
		   String title,
		   String description,
		   String imageUrl,
		   String category,
		   double price
   ) throws ProductNotFoundException {

	  FakeStoreDto request = new FakeStoreDto ();
	  request.setTitle (title);
	  request.setDescription (description);
	  request.setImage (imageUrl);
	  request.setCategory (category);
	  request.setPrice (price);

	  request.setId (productId);
	  return request.toProduct ();
   }

   //   Put Product
   @Override
   public Product replaceProduct (
		   Long productId,
		   String title,
		   String description,
		   String imageUrl,
		   String category,
		   double price

   ) throws ProductNotFoundException {

	  FakeStoreDto requestDto = new FakeStoreDto ();

	  requestDto.setId (productId);
	  requestDto.setTitle (title);
	  requestDto.setDescription (description);
	  requestDto.setPrice (price);
	  requestDto.setImage (imageUrl);
	  requestDto.setCategory (category);

//	  Create request entity to send in put request body to fakestore
	  HttpEntity<FakeStoreDto> requestEntity = new HttpEntity<> (requestDto);

	  FakeStoreDto responseDto = restTemplate.exchange (
			  "https://fakestoreapi.com/products/" + productId,
			  HttpMethod.PUT, requestEntity, FakeStoreDto.class
	  ).getBody ();

	  if (responseDto == null) {
		 throw new ProductNotFoundException (
				 "Product id with " + productId + "not found, try something different"
		 );
	  }
	  return responseDto.toProduct ();
   }

}
