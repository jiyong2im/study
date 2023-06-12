package com.springboot.relationship.repository;

import com.querydsl.core.types.Predicate;
import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.entity.QProduct;
import com.springboot.relationship.data.repository.QProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class QProductRepositoryTest {
    @Autowired
    QProductRepository qProductRepository;

    @Test
    public void queryDSLTest1(){
        Predicate predicate = QProduct.product.name.containsIgnoreCase("펜")
                .and(QProduct.product.price.between(1000,2500));

        Optional<Product> foundProduct = qProductRepository.findOne(predicate);

        if(foundProduct.isPresent()){
            Product product = foundProduct.get();
            System.out.println(product.getNumber());
            System.out.println(product.getName());
            System.out.println(product.getPrice());
            System.out.println(product.getStock());
        }
    }
    @Test
    public void queryDSLTest2() {
        QProduct qProduct = QProduct.product;
        Iterable<Product> productList = qProductRepository.findAll(
                qProduct.name.contains("펜펜")
                        .and(qProduct.price.between(550,1500))
        );

        for(Product product: productList){
            System.out.println(product.getNumber());
            System.out.println(product.getName());
            System.out.println(product.getPrice());
            System.out.println(product.getStock());
        }

    }

}
