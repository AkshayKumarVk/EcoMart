package com.example.product_service_12052024.services;

import com.example.product_service_12052024.dtos.FakeStoreDto;
import com.example.product_service_12052024.exception.ProductNotFoundException;
import com.example.product_service_12052024.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService {

   private final RestTemplate restTemplate;

   public FakeStoreProductService (RestTemplate restTemplate) {
	  this.restTemplate = restTemplate;

   }

   //   route from ProductService interface
   @Override
   public List<Product> getAllProducts () {
	  FakeStoreDto[] fakeStoreDtoList = restTemplate.getForObject (
			  "https://fakestoreapi.com/products/",
			  FakeStoreDto[].class
	  );

	  List<Product> products = new ArrayList<> ();

	  for (FakeStoreDto fakeStoreDto : fakeStoreDtoList) {
		 products.add (fakeStoreDto.toProduct ());
	  }
	  return products;
   }


   @Override
   public Product getSingleProduct (Long productId) throws ProductNotFoundException {

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
	  return fakeStoreDto.toProduct ();
   }


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
	  return response.toProduct ();


   }
   public Product deleteProduct (Long productId)throws ProductNotFoundException {
		FakeStoreDto fakeStoreDto =restTemplate.exchange (
				"http://fakestoreapi.com/products/"+productId,
				HttpMethod.DELETE, null, FakeStoreDto.class
		).getBody ();

		if (fakeStoreDto==null){
		   throw new ProductNotFoundException (
				   "Product with id "+productId+"not found, try deleting a product with id less than 21"
		   );
		}

		return fakeStoreDto.toProduct ();
   }
}
