package com.example.product_service_12052024.controllers;

import com.example.product_service_12052024.models.Category;
import com.example.product_service_12052024.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")

public class CategoryController {

   private CategoryService categoryService;


//   public CategoryController( CategoryService categoryService){
//	  this.categoryService=categoryService;
//   }
//
//   @GetMapping()
//   public List<Category> categories=categoryService.getAllCategories ();



}
