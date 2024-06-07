package com.example.product_service_12052024.repositories;

import com.example.product_service_12052024.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {


   Category save(Category category);

   Category findByTitle(String title);

   List<Category> findByTitleEndingWith (String mobiles);
}
