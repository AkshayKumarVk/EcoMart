package com.example.product_service_12052024.controllers;

import com.example.product_service_12052024.dtos.categorydtos.CategoryResponseDto;
import com.example.product_service_12052024.exception.CategoryNotFoundException;
import com.example.product_service_12052024.models.Category;
import com.example.product_service_12052024.services.categoryservices.CategoryService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")

public class CategoryController {

   private final CategoryService categoryService;


   public CategoryController (@Qualifier("selfCategoryService")
							  CategoryService categoryService) {
	  this.categoryService = categoryService;
   }

   @GetMapping("/all")
   public ResponseEntity<List<CategoryResponseDto>> getAllCategories ()
		   throws CategoryNotFoundException {

	  List<Category> categories = categoryService.getAllCategories ();

	  if (categories.isEmpty ()){
		 throw new CategoryNotFoundException ("No category found");
	  }

	  List<CategoryResponseDto> categoryResponseDtos = new ArrayList<> ();


	  for (Category category : categories) {
		 categoryResponseDtos.add (from (category));
	  }
	  return new ResponseEntity<> (categoryResponseDtos, HttpStatus.OK);
   }


   public CategoryResponseDto from (Category category) {
	  CategoryResponseDto categoryResponseDto = new CategoryResponseDto ();

	  categoryResponseDto.setCategoryName (category.getTitle ());
	  return categoryResponseDto;
   }
}
