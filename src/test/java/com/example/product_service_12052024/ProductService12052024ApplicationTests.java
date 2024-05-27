package com.example.product_service_12052024;

import com.example.product_service_12052024.models.Product;
import com.example.product_service_12052024.repositories.ProductRepository;
import com.example.product_service_12052024.repositories.projections.ProductProjection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductService12052024ApplicationTests {


   @Autowired
   private ProductRepository productRepository;

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
   void testNativeSQL(){
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

}
