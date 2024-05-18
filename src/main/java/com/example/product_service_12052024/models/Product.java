package com.example.product_service_12052024.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
   
   
   private Long id;
   private String title;
   private String description;
   private Double price;
   private String imageUrl;
   private Category category;
   
}
