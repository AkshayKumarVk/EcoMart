package com.example.product_service_12052024.services;

import com.example.product_service_12052024.dtos.FakeStoreDto;
import com.example.product_service_12052024.dtos.ProductResponseDto;
import com.example.product_service_12052024.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FakeStoreProductService implements ProductService {
   
   private RestTemplate restTemplate;
   
   public FakeStoreProductService(RestTemplate restTemplate) {
	  this.restTemplate = restTemplate;
	  
   }
   
//   route from ProductService interface
   @Override
   public ProductResponseDto getSingleProduct(int productId) {
	  
//	  send to fakeStore dto
	  FakeStoreDto fakeStoreDto = restTemplate.getForObject(
			  "https://fakestoreapi.com/products/" + productId,
			  FakeStoreDto.class	//get response back and converted to FakeStoreDto
	  );
//	  convert to response to controller
	  return fakeStoreDto.toProductResponceDto();
   }
   
   @Override
   public ProductResponseDto addProduct(
		   String title,
		   String description,
		   String imageUrl,
		   String category,
		   double price
   ) {
	  FakeStoreDto fakeStoreDto = new FakeStoreDto();
	  fakeStoreDto.setTitle(title);
	  fakeStoreDto.setDescription(description);
	  fakeStoreDto.setImage(imageUrl);
	  fakeStoreDto.setCategory(category);
	  fakeStoreDto.setPrice(price);
	  
	  FakeStoreDto response = restTemplate.postForObject(
			  "https://fakestoreapi.com/products/",
			  fakeStoreDto,
			  FakeStoreDto.class
	  );
//	  sending to Fake store class object.
	  return response.toProductResponceDto();
   }
}
