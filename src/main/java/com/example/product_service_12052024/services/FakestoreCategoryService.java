package com.example.product_service_12052024.services;

import com.example.product_service_12052024.models.Category;

import java.util.List;

public class FakestoreCategoryService implements CategoryService{
   @Override
   public List<Category> getAllCategories () {
	  return List.of ();
   }
}
