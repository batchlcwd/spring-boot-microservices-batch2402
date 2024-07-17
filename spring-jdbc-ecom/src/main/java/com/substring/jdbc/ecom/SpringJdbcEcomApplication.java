package com.substring.jdbc.ecom;

import com.substring.jdbc.ecom.dao.ProductDao;
import com.substring.jdbc.ecom.model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class SpringJdbcEcomApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringJdbcEcomApplication.class, args);
        // product create karenge:
        Product product1 = new Product(101, "Samsung Galaxy", "Samsung Galaxy M31", 13000);
        ProductDao productDao = context.getBean(ProductDao.class);
        Product product = productDao.create(product1);
        System.out.println(product);

        Scanner scanner = new Scanner(System.in);
        int input;
        while (true) {
            System.out.println("Enter a number: ");
            input = scanner.nextInt();
            String result = switch (input) {
                case 1 -> updateProduct(context);
                case 2 -> deleteProduct(context);
                case 3 -> "You selected Option 3!";
                case 4 -> "You selected Option 4!";
                case 5 -> "You selected Option 5!";
                case 6 -> "You selected Option 6!";
                case 7 -> "Goodbye!";
                default -> "Invalid input. Try again!";

            };
            System.out.println(result);
            if (input == 7) {
                break;
            }
        }
        scanner.close();
    }

    private static String updateProduct(ConfigurableApplicationContext context) {
        Product product1 = new Product(104, "Samsung Galaxy", "Samsung Galaxy M31", 13000);
        ProductDao productDao = context.getBean(ProductDao.class);
        Product product = productDao.update(product1, "Samsung Galaxy M21");
        return "Your product is updated by " + product.getDescription();
    }

    private static String deleteProduct(ConfigurableApplicationContext context) {
        Product product2 = new Product(104, "Samsung Galaxy", "Samsung Galaxy M31", 13000);
        ProductDao productDao = context.getBean(ProductDao.class);
        productDao.delete(product2.getId());
        return "Your product " + product2.getDescription() + " is removed from your cart now";
    }


}
