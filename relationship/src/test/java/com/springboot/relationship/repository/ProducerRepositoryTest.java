package com.springboot.relationship.repository;

import com.springboot.relationship.data.entity.Producer;
import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.repository.ProductRepository;
import com.springboot.relationship.data.repository.support.ProducerRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
public class ProducerRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProducerRepository producerRepository;

    @Test
    @Transactional
    @DisplayName("ManyToMany")
    void test(){
        Product product1 = saveProduct("동글맨",500, 1000);
        Product product2 = saveProduct("정지용",100, 122);
        Product product3 = saveProduct("호박수",600, 1532);

        Producer producer1 = saveProducer("flature");
        Producer producer2 = saveProducer("sunjong");

        producer1.addProduct(product1);
        producer1.addProduct(product2);

        producer2.addProduct(product2);
        producer2.addProduct(product3);
        producerRepository.saveAll(Lists.newArrayList(producer1,producer2));

        System.out.println("걸림");
        System.out.println(producerRepository.findById(producer1.getId()));
        System.out.println("걸림");
        System.out.println(producerRepository.findById(producer2.getId()).get().getProducts());

    }
    private Product saveProduct(String name, Integer price, Integer stock){
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);
        return productRepository.save(product);
    }
    private Producer saveProducer(String name){
        Producer producer = new Producer();
        producer.setName(name);
        return producerRepository.save(producer);
    }
    @Test
    @Transactional
    @DisplayName("ManyToMany + ManyToMany")
    void test2(){

        Product product1 = saveProduct("동글맨",500, 1000);
        Product product2 = saveProduct("시방마",100, 122);
        Product product3 = saveProduct("호박수",600, 1532);

        Producer producer1 = saveProducer("flature");
        Producer producer2 = saveProducer("jiyong");

        producer1.addProduct(product1);
        producer1.addProduct(product2);
        producer2.addProduct(product2);
        producer2.addProduct(product3);

        product1.addProducer(producer1);
        product2.addProducer(producer1);
        product2.addProducer(producer2);
        product3.addProducer(producer2);

        producerRepository.saveAll(Lists.newArrayList(producer1, producer2));
        productRepository.saveAll(Lists.newArrayList(product1, product2, product3));

        System.out.println("프로덕트에서 검색 ~ products ~" + producerRepository.findById(1L).get().getProducts());
        System.out.println("프로듀서에서 검색 ~ producer ~" + productRepository.findById(2L).get().getProducers());


    }
}
