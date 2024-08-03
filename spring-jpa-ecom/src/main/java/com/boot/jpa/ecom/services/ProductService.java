package com.boot.jpa.ecom.services;

import com.boot.jpa.ecom.entities.Category;
import com.boot.jpa.ecom.entities.Product;
import com.boot.jpa.ecom.exception.ResourceNotFoundException;
import com.boot.jpa.ecom.repositories.ProductRepository;
import com.boot.jpa.ecom.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {


    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    //create product

    public Product create(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    //find all

    public List<Product> all() {
        List<Product> all = productRepository.findAll();
        return all;
    }


    // find by id

    public Product byId(int productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product Not found with id " + productId));
        return product;
    }


    public void delete(int productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found !!"));
        productRepository.delete(product);
    }

    public List<Product> getProductByPrice(double price){
        List<Product> byPrice = productRepository.findByPrice(price);
        return byPrice;
    }



    //save one random category and one random product

    @Transactional
    public void transactCategoryWithProduct(){
        //save category
        Category category = new Category();
        category.setTitle("Random Category2");
        categoryRepository.save(category);
        //save product
        Product product = new Product();
        product.setTitle("Random Product 2");
        product.setPrice(2450);
        product.setLive(true);
        product.setDescription("This is random product for transaction test, asgfosahflsafhosajhfsabfosajfsabifuhasofbashfoasbfhsahjfosbafiosahfoasjfoasbfiosadhfopasjhfoasghdfoasdbfosahjfosadhfsaohfashfhopsahfasufsadhgfoisafjhosaghfsafhsadhfasfasdhfoashfsahyfopsahfaohfoasdhtsadohfsaduohfosafhsadfhjgpasjgfoasjhpsahgfjpsajgiosdjgfpsjhfosajfgsahfohsadfhsaofhsadofjasfhosafosadhfoisahfoshgfosahfaosghsaofbhasofhasogfhoasdghfosadihfgsahfosadhfgosafghbasohfgosahgisadghdfisagisahfdsoaidhgoashfosadhfgosadhgfodsahgfoashgfoashaghoisghsodgfhasogfhsaogfhsaoghsaoghsaighosadhgosajhgsahjgosaosahfosafsaghfoasfasdofasdfhoasdhfoashfoashf");
        productRepository.save(product);

    }


}



