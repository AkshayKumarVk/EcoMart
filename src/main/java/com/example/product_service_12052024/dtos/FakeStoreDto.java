package com.example.product_service_12052024.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreDto {
   
   private int id;
   private String title;
   private String description;
   private double price;
   private String image;
   private String category;
   
   public ProductResponseDto toProductResponceDto() {
      ProductResponseDto productResponseDto = new ProductResponseDto();
      productResponseDto.setId(this.id);
      productResponseDto.setTitle(this.title);
      productResponseDto.setDescription(this.description);
      productResponseDto.setPrice(this.price);
      productResponseDto.setImage(this.image);
      productResponseDto.setCategory(this.category);
//      response to the controller
      return productResponseDto;
   }
   
}
