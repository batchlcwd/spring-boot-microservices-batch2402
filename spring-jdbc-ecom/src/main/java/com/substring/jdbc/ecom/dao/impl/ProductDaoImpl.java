package com.substring.jdbc.ecom.dao.impl;

import com.substring.jdbc.ecom.dao.ProductDao;
import com.substring.jdbc.ecom.model.Product;
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
        String query = "insert into products(id,title,description,price) SELECT * FROM( SELECT ?,?,?,?) AS tmp WHERE NOT EXISTS (SELECT id FROM products WHERE id=?) LIMIT 1";
        int update = jdbcTemplate.update(
                query,
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice(), product.getId());
        System.out.println(update + "rows effected");
        return product;
    }

    @Override
    public Product update(Product product, String description) {
        String query = "update products set id = ?, title = ?, description = ?, price = ? where description =?";
        int update = jdbcTemplate.update(
                query,
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice(), description);
        System.out.println(update + "rows effected");
        return product;
    }

    @Override
    public void delete(int productId) {
        String query = "delete from products where id =?";
        int delete = jdbcTemplate.update(
                query,
                productId);
        System.out.println(delete + "rows effected");
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
