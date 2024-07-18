package com.substring.jdbc.ecom.dao;

import com.substring.jdbc.ecom.model.Product;
import com.substring.jdbc.ecom.model.ProductWithCategory;

import java.util.List;

public interface ProductDao {

    //save product
    Product create(Product product);


    //update product
    Product update(Product product, int productId);

    //delete product
    void delete(int productId);

    // get all product
    List<Product> getAll();

    // get single product
    Product get(int productId);

    // search product
    List<Product> search(String keyword);

    // get all product of category
    List<Product> getAllByCategory(int catId);

//    get all products with category
    List<ProductWithCategory> getAllWithCategory();
}
