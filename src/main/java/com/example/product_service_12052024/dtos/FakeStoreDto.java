package com.example.product_service_12052024.dtos;


import com.example.product_service_12052024.models.Category;
import com.example.product_service_12052024.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreDto {

   private Long id;
   private String title;
   private String description;
   private double price;
   private String image;
   private String category;

   public Product toProduct () {

	  Product product = new Product ();

	  product.setId (this.id);
	  product.setTitle (this.title);
	  product.setDescription (this.description);
	  product.setPrice (this.price);
	  product.setImageUrl (this.image);

	  Category categoryObj = new Category ();
	  categoryObj.setTitle (category);

	  product.setCategory (categoryObj);

//      response to the controller
	  return product;
   }

}
