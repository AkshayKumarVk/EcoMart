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
   void testFetchMode () {
	  List<Category> categories = categoryRepository.findByTitleEndingWith ("Mobiles");

	  for (Category cats : categories) {
		 System.out.println (cats.getTitle ());

		 List<Product> productList = cats.getProducts ();
		 for (Product products : productList)
			System.out.println (products.getTitle ());
	  }
   }

//   @Test
//   void addRandomProducts () {
//
//	  String[] productCategories = {"Apple Mobiles", "Samsung Store", "Xiaomi Mobiles", "Vivo Phones",
//			  "Asus Phones", "Poco Phones", "Nokia Mobiles"};
//	  String[] productTitles = {"C210", "G400 5G", "M6 Pro", "X5 5G", "F5 5G", "Phone 8", "Phone 6", "X100",
//			  "13T"};
//	  String imageUrl = "www.image.com";
//	  String[] descriptions = {"16 gb ram", "12 gb ram", "5 camera", "88W fast charging", "24W fast charging"};
//
//	  Random random = new Random ();
//
//	  for (int i = 0; i <= 100; i++) {
//		 int randomCategoryIndex = random.nextInt (productCategories.length);
//		 int randomTitleIndex = random.nextInt (productTitles.length);
//		 int randomDescriptionIndex = random.nextInt (descriptions.length);
//
//		 double minPrice=99;
//		 double maxPrice=999;
//		 double randomProductPrice =minPrice+(maxPrice-minPrice)*Math.random();
//		 DecimalFormat df= new DecimalFormat ("#.00");
//		 randomProductPrice= Double.parseDouble (df.format ((randomProductPrice)));
//
//		 Product product = new Product ();
//		 product.setTitle (productTitles[randomTitleIndex]);
//		 product.setDescription (descriptions[randomDescriptionIndex]);
//		 product.setPrice (randomProductPrice);
//
//		 Category category = new Category ();
//		 category.setTitle (productCategories[randomCategoryIndex]);
//		 product.setCategory (category);
//		 product.setImageUrl (imageUrl);
//
//		 productRepository.save (product);
//
//	  }
//   }

   @Test
   void titles () {
	  List<ProductProjection> products = productRepository.findProductsByCategoryName (8);

	  for (ProductProjection product : products) {
		 System.out.println ("Cid:	" + product.getId ());
		 System.out.println ("Phones:	" + product.getTitle ());
		 System.out.println ("Price:	" + product.getPrice ());
	  }
   }
}