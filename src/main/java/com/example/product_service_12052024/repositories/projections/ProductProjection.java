package com.example.product_service_12052024.repositories.projections;

import com.example.product_service_12052024.models.Category;

public interface ProductProjection {

   Long getId();
   String getTitle();
   String getDescription();
   Double getPrice();
   String getImageUrl ();
   Category getCategory();
}
