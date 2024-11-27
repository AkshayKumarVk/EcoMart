package com.example.product_service_12052024.models;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Getter
@Setter

@Entity
@Table(name ="categories")
public class Category extends BaseModel {

   @OneToMany(mappedBy = "category")
   @Fetch(value = FetchMode.SUBSELECT)
   List<Product> products;
   private String title;

}
