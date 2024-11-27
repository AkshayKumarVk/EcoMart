package com.example.product_service_12052024.services.categoryservices;

import com.example.product_service_12052024.exception.CategoryNotFoundException;
import com.example.product_service_12052024.models.Category;

import java.util.List;

public interface CategoryService {

   List<Category> getAllCategories ()
		   throws CategoryNotFoundException;
}
