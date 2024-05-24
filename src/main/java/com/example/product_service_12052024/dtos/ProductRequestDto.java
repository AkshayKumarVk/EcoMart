package com.example.product_service_12052024.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
   private Long id;
   private String title;
   private String description;
   private double price;
   private String imageUrl;
   private String category;
}
