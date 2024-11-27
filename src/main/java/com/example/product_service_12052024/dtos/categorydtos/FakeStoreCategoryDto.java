package com.example.product_service_12052024.dtos.categorydtos;

import com.example.product_service_12052024.models.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
class FakeStoreCategoryDto{
   private String categoryName;

   public Category toCategory() {
	  Category category = new Category();
	  category.setTitle(this.getCategoryName());
	  return category;
   }

//   public static FakeStoreCategoryDto from(Category category) {
//	  FakeStoreCategoryDto dto = new FakeStoreCategoryDto();
//	  dto.setCategoryName(category.getTitle());
//	  return dto;
//   }
}