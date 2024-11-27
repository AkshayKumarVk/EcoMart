package com.example.product_service_12052024.services.categoryservices;

import com.example.product_service_12052024.exception.CategoryNotFoundException;
import com.example.product_service_12052024.models.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreCategoryService")
public class FakestoreCategoryService implements CategoryService {
   private static final Logger logger = (Logger) LoggerFactory.getLogger (FakestoreCategoryService.class);
   private final RestTemplate restTemplate;


   public FakestoreCategoryService (RestTemplate restTemplate) {
	  this.restTemplate = restTemplate;
   }

   @Override
   public List<Category> getAllCategories ()
		   throws CategoryNotFoundException {

	  String[] categoryNames = restTemplate.getForObject (
			  "https://fakestoreapi.com/products/categories", String[].class
	  );

	  logger.debug ("Fetching category names", (Object[]) categoryNames);

	  if (categoryNames == null && categoryNames.length == 0) {
		 throw new CategoryNotFoundException ("No categories found!");
	  }

	  List<Category> categories = new ArrayList<> ();

	  for (String categoryName : categoryNames) {
		 Category category = new Category ();
		 category.setTitle (categoryName);
		 categories.add (category);
	  }
	  return categories;
   }
}
