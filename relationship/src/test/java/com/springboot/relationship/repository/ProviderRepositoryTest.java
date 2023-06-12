package com.springboot.relationship.repository;

import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.entity.Provider;
import com.springboot.relationship.data.repository.ProductRepository;
import com.springboot.relationship.data.repository.ProviderRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.security.ProviderException;
import java.util.List;

@SpringBootTest
public class ProviderRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProviderRepository providerRepository;

    @Test
    @DisplayName("product <-> provider 1:n, n:1 양방향")
    void relationshipTest(){
        Provider provider = new Provider();
        provider.setName("ㅇㅇ상사");
        providerRepository.save(provider);

        Product product1 = new Product();

        product1.setName("펜");
        product1.setPrice(2000);
        product1.setStock(200);
        product1.setProvider(provider);

        Product product2 = new Product();
        product2.setName("가방");
        product2.setPrice(30000);
        product2.setStock(300);
        product2.setProvider(provider);

        Product product3 = new Product();
        product3.setName("노트");
        product3.setPrice(40000);
        product3.setStock(400);
        product3.setProvider(provider);

        //provider.addProductList(product1);
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        List<Product> products = providerRepository.findById(provider.getId()).get().getProductList();

        for(Product product : products){
            System.out.println("하이");
            System.out.println(product);
        }
    }

    @Test
    @DisplayName("product <-> provider m:1")
    void relationshipTest1(){
        //우선 프린트 문에서 하나씩 비워져서 나옴
        Provider provider = new Provider();
        provider.setName("ㅇㅇ물산");
        providerRepository.save(provider);

        Provider provider1 = new Provider();
        provider1.setName("보짓물산");
        providerRepository.save(provider1);



        Product product = new Product();
        product.setName("가위");
        product.setPrice(5000);
        product.setStock(500);
        product.setProvider(provider);

        Product product2 = new Product();
        product2.setName("가위");
        product2.setPrice(5000);
        product2.setStock(500);
        product2.setProvider(provider1);


        productRepository.save(product);
        productRepository.save(product2);

        System.out.println("프로덕트" + productRepository.findById(product2.getNumber()).get());

        System.out.println("프로바이더" + productRepository.findById(product2.getNumber()).orElseThrow(RuntimeException::new).getProvider());
    }


    @Test
    @DisplayName("Cascade Test")
    void cascadeTest() {
        Provider provider = savedProvider("새로운 공급업체");

        Product product1 = savedProduct("상품1", 1000, 100);
        Product product2 = savedProduct("상품2", 2000, 200);
        Product product3 = savedProduct("상품3", 3000, 300);

        product1.setProvider(provider);
        product2.setProvider(provider);
        product3.setProvider(provider);

        provider.getProductList().addAll(Lists.newArrayList(product1,product2,product3));

        providerRepository.save(provider);
    }
    private Provider savedProvider(String name){
        Provider provider = new Provider();
        provider.setName(name);
        return provider;
    }
    private Product savedProduct(String name, Integer price, Integer stock) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);

        return product;

    }

}
