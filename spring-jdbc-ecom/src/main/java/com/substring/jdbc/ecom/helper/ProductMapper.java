package com.substring.jdbc.ecom.helper;

import com.substring.jdbc.ecom.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setTitle(rs.getString("title"));
        product.setPrice(rs.getInt("price"));
        product.setDescription(rs.getString("description"));
        //set the  values of products
        return product;
    }
}
