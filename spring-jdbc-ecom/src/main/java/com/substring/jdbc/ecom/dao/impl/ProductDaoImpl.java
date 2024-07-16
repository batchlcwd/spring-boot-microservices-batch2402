package com.substring.jdbc.ecom.dao.impl;

import com.substring.jdbc.ecom.dao.ProductDao;
import com.substring.jdbc.ecom.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {


    private JdbcTemplate jdbcTemplate;

    //by default : constructor injection

    public ProductDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        String createQuery = "CREATE TABLE IF NOT EXISTS products(id int primary key, title varchar(200) not null, description varchar(500) not null , price int not null)";
        jdbcTemplate.update(createQuery);

    }

    @Override
    public Product create(Product product) {
        String query = "insert into products(id,title,description,price) values(?,?,?,?)";
        int update = jdbcTemplate.update(
                query,
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice());
        System.out.println(update + "rows effected");
        return product;
    }

    @Override
    public Product update(Product product, int productId) {
        //TODO
        return null;
    }

    @Override
    public void delete(int productId) {

        //TODO
    }

    @Override
    public List<Product> getAll() {
        //TODO
        return null;
    }

    @Override
    public Product get(int productId) {
        //TODO
        return null;
    }

    @Override
    public List<Product> search(String keyword) {
        return null;
    }

    @Override
    public List<Product> getAllByCategory(int catId) {
        return null;
    }
}
