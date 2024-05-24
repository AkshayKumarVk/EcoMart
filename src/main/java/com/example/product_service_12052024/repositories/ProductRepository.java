package com.example.product_service_12052024.repositories;

import com.example.product_service_12052024.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

   //   Add Product
   Product save (Product product);

   //   Get All Products
   List<Product> findAll ();

//   Get Single Product
   Product findByIdIs(Long id);

}
