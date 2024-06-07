package com.example.product_service_12052024.repositories;

import com.example.product_service_12052024.models.Product;
import com.example.product_service_12052024.repositories.projections.ProductProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

   //   Add Product
   Product save (Product product);

   //   Get All Products
   List<Product> findAll ();

   //   Get Single Product
   Product findByIdIs (Long id);

   List<Product> findByCategory_Title (String title);

   List<Product> findByCategory_TitleContaining (String title);

   @Query("select p from Product p where p.category.title=:categoryName")
   List<Product> getProductWithCategoryName (String categoryName);


   @Query("select p.title title from  Product p where p.category.title=:categoryName")
   List<String> getSpecificProductTitle (String categoryName);


   @Query("SELECT p.id id , p.title  title from Product p where p.category.title=:categoryTitle")
   List<ProductProjection> someProductWithTitle (String categoryTitle);


   @Query("select p.id id, p.title title, p.price price from Product p where p.category.title=:categoryTitle")
   List<ProductProjection> productWithIdAndTitle (String categoryTitle);


//   Native Query

   @Query(value = "select p.id AS id, p.title as title, p.price as price from product_service.product p where p.category_id=:categoryId", nativeQuery = true)
   List<ProductProjection> productWithId (Long categoryId);


   //   Native Query 2
   @Query(value = "select p.id as id, p.title as title, p.price as price from product_service.product p join product_service.category c on p.category_id = c.id where c.title=:categoryTitle", nativeQuery = true)
   List<ProductProjection> productWithCatTitle (String categoryTitle);
}

