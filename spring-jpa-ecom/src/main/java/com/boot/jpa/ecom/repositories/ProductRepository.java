package com.boot.jpa.ecom.repositories;

import com.boot.jpa.ecom.entities.Product;
import com.boot.jpa.ecom.entities.ProductWithCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {

    //1. CRUD operation is there for you.
    //PROTOCOL
    //2. Custom methods (custom finder methods)

    //custom method for like query

//    SELECT * FROM product WHERE title LIKE '%in%';

    //test :
    List<Product> findByTitleContaining(String keyword);

    List<Product> findByTitleContainsIgnoreCase(String keyword);
    List<Product> findByTitleNotContaining(String keyword);

   // "%max%"
    List<Product> findByTitleNotLike(String keywordWithWild);
    List<Product> findByTitleStartsWith(String prefixTitle);
    List<Product> findByTitleEndsWith(String suffixTitle);


    List<Product> findByPrice(double price);

    // method
    List<Product> findByTitleAndPrice(String title, double price);

    List<Product> findByIsLive(boolean isLive);

    int countByPrice(double price);

    boolean existsByTitle(String title);

    // 3.query methods

    @Query("select  p from Product p")
    List<Product> getMyCustomQueryProducts();


    @Query(nativeQuery = true, value = "select * from jpa_products")
    List<Product> getMyCustomNativeQueryProducts();

    // parameters

    @Query("select p from Product p WHERE p.title =:title and p.price =:price")
    List<Product> getProductByTitle(@Param("title") String title, @Param("price") double price);

    @Query("select  p from Product  p where p.title LIKE %:keyword%")
    List<Product> searchProductsByTitle(@Param("keyword") String keywords);

    //method to join query and fetch  the result

    @Query("select p from Product p JOIN fetch p.category where p.category.title =:catTitle")
    List<Product> getProductByCategoryTitle(@Param("catTitle") String title);

//    @Query("")
//    List<ProductWithCategory> getProductWithCategory(String title);

}
