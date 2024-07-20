package com.boot.jpa.ecom.repositories;

import com.boot.jpa.ecom.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface ProductRepository extends JpaRepository<Product,Integer>
{
//CRUD
}
