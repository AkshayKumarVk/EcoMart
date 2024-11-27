package com.example.product_service_12052024.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends BaseModel {

   private String title;
   private String description;
   private Double price;
   private String imageUrl;
   @ManyToOne(cascade = CascadeType.PERSIST)
   @JoinColumn
   private Category category;

}
