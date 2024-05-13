package com.example.product_service_12052024.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDto {
   private int id;
   private String title;
   private String description;
   private double price;
   private String image;
   private String category;
}
