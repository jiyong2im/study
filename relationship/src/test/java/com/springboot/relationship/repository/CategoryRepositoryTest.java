package com.springboot.relationship.repository;

import com.springboot.relationship.data.entity.Category;
import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.repository.CategoryRepository;
import com.springboot.relationship.data.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @DisplayName("OneToMany")
    void test() {
        Product product = new Product();
        product.setName("펜");
        product.setPrice(4000);
        product.setStock(2000);

        productRepository.save(product);

        Category category = new Category();
        category.setCode("S1");
        category.setName("도서");
        category.getProducts().add(product);

        categoryRepository.save(category);

        List<Product> products = categoryRepository.findById(category.getId()).get().getProducts();

        for(Product foundProduct : products){
            System.out.println(products);
            System.out.println(foundProduct);

        }
    }


}
