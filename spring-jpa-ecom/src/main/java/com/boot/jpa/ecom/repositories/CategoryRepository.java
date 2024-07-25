package com.boot.jpa.ecom.repositories;

import com.boot.jpa.ecom.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public interface CategoryRepository  extends JpaRepository<Category,Integer>
{

}
