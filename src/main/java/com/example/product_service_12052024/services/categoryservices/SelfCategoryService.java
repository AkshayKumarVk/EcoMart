package com.example.product_service_12052024.services.categoryservices;

import com.example.product_service_12052024.exception.CategoryNotFoundException;
import com.example.product_service_12052024.models.Category;
import com.example.product_service_12052024.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfCategoryService")
public class SelfCategoryService implements CategoryService {
   private final CategoryRepository categoryRepository;

   public SelfCategoryService (CategoryRepository categoryRepository) {
	  this.categoryRepository = categoryRepository;
   }

   public List<Category> getAllCategories ()
		   throws CategoryNotFoundException {
//	  List<Category> categories = categoryRepository.findAll ();

	  List<Category> categories = categoryRepository.findAllUniqueCategories ();

	  if (categories.isEmpty ()) {
		 throw new CategoryNotFoundException ("No category found!");
	  }

	  return categories;
   }
}
