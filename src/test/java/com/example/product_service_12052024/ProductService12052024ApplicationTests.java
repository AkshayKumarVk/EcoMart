package com.example.product_service_12052024;

import com.example.product_service_12052024.models.Category;
import com.example.product_service_12052024.models.Product;
import com.example.product_service_12052024.repositories.CategoryRepository;
import com.example.product_service_12052024.repositories.ProductRepository;
import com.example.product_service_12052024.repositories.projections.ProductProjection;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductService12052024ApplicationTests {


   @Autowired
   private ProductRepository productRepository;
   @Autowired
   private CategoryRepository categoryRepository;

   @Test
   void contextLoads () {
   }


   @Test
   void testJpaDeclaredJoins () {
	  List<Product> products = productRepository.findByCategory_Title ("Apple iPhone 14");

	  for (Product product : products) {
		 System.out.println (product.getTitle ());
	  }
   }

   @Test
   void testContains () {
	  List<Product> products = productRepository.findByCategory_TitleContaining ("store");
	  for (Product product : products) {
		 System.out.println (product.getTitle ());
	  }
   }


   @Test
   void testHql () {
	  List<Product> products = productRepository.getProductWithCategoryName ("Samsung Store");
	  for (Product product : products) {
		 System.out.println (product.getTitle ());
		 System.out.println (product.getCategory ().getTitle ());
		 System.out.println (product.getPrice ());
		 System.out.println ();
	  }
   }


   @Test
   void testTitles () {
	  List<String> productTitles = productRepository.getSpecificProductTitle ("Samsung Store");

	  for (String productTitle : productTitles) {
		 System.out.println (productTitle);
	  }

   }

   @Test
   void testProjections () {
	  List<ProductProjection> products = productRepository.someProductWithTitle ("Samsung Store");

	  for (ProductProjection product : products) {
		 System.out.println (product.getId ());
		 System.out.println (product.getTitle ());
	  }

	  List<ProductProjection> productProjections = productRepository.productWithIdAndTitle ("Apple Mobiles");

	  for (ProductProjection productProjection : productProjections) {
		 System.out.println (productProjection.getId ());
		 System.out.println (productProjection.getTitle ());
		 System.out.println (productProjection.getPrice ());
	  }
   }


   //   Native Query
   @Test
   void testNativeSQL () {
	  List<ProductProjection> productProjections = productRepository.productWithId (2L);

	  for (ProductProjection productProjection : productProjections) {
		 System.out.println (productProjection.getId ());
		 System.out.println (productProjection.getTitle ());
		 System.out.println (productProjection.getPrice ());
	  }
   }


   @Test
   void testNativeSQL2 () {
	  List<ProductProjection> productProjections = productRepository.productWithCatTitle ("Apple Mobiles");

	  for (ProductProjection productProjection : productProjections) {
		 System.out.println (productProjection.getId ());
		 System.out.println (productProjection.getTitle ());
		 System.out.println (productProjection.getPrice ());
	  }
   }

   @Test
   @Transactional
   void testFetchModes () {
	  Optional<Category> category = categoryRepository.findById (1L);
	  if (category.isPresent ()) {
		 System.out.println (category.get ().getTitle ());

		 List<Product> products = category.get ().getProducts ();
		 for (Product product : products) {
			System.out.println (product.getTitle ());
		 }
	  }
   }

   @Test
   @Transactional
   void testFetchMode(){
	  List<Category> categories=categoryRepository.findByTitleEndingWith("Mobiles");

	  for (Category cats: categories){
		 System.out.println (cats.getTitle ());

		 List<Product> productList= cats.getProducts ()	;
		  for (Product products:productList)
			 System.out.println (products.getTitle ());
	  }
   }
}
