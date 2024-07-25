package com.boot.jpa.ecom.repositories;

import com.boot.jpa.ecom.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {

    //1. CRUD operation is there for you.

    //PROTOCOL
    //2. Custom methods (custom finder methods)
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

    @Query("select p from Product p WHERE p.title =:title and p.price =: price")
    List<Product> getProductByTitle(@Param("title") String title, @Param("price") double price);

}
