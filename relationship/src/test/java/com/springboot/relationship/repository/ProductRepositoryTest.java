package com.springboot.relationship.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.relationship.data.entity.Product;
import com.springboot.relationship.data.entity.QProduct;
import com.springboot.relationship.data.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

//@WebAppConfiguration
@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Test
    @DisplayName("정렬 메소드 테스트")
    void sortingAndPagingTest(){
        Product product1 = new Product();
        product1.setName("펜");
        product1.setPrice(1000);
        product1.setStock(100);
        product1.setCreatedAt(LocalDateTime.now());
        product1.setUpdatedAt(LocalDateTime.now());

        Product product2 = new Product();
        product2.setName("펜슬");
        product2.setPrice(5000);
        product2.setStock(300);
        product2.setCreatedAt(LocalDateTime.now());
        product2.setUpdatedAt(LocalDateTime.now());

        Product product3 = new Product();
        product3.setName("펜잡이");
        product3.setPrice(500);
        product3.setStock(50);
        product3.setCreatedAt(LocalDateTime.now());
        product3.setUpdatedAt(LocalDateTime.now());

        Product savedProduct1 = productRepository.save(product1);
        Product savedProduct2 = productRepository.save(product2);
        Product savedProduct3 = productRepository.save(product3);
        System.out.println(productRepository.findByName("펜", Sort.by(Order.asc("price"))));
        System.out.println(productRepository.findByName("펜", Sort.by(Order.asc("price"), Order.desc("stock"))));
        System.out.println(productRepository.findByName("펜", getSort()));

    }
    private Sort getSort() {
        return Sort.by(Order.asc("price"), Order.desc("stock"));
    }

    @Test
    void page(){

        Page<Product> productPage = productRepository.findByName("펜", PageRequest.of(1,5));
        System.out.println(productPage.getContent());// 배열 형태로 값 출력
    }


    @PersistenceContext
    EntityManager entityManager;
    @Test
    void queryDslTest() {
        JPAQuery<Product> query = new JPAQuery(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = query
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();

        for (Product product : productList) {
            System.out.println("-------------------");
            System.out.println();
            System.out.println("Product Number : " + product.getNumber());
            System.out.println("Product Name : " + product.getName());
            System.out.println("Product Price : " + product.getPrice());
            System.out.println("Product Stock : " + product.getStock());
            System.out.println();
            System.out.println("-------------------");
        }
    }
        @Test
        void queryDslTest2() {
            JPAQueryFactory jpqQueryFactory= new JPAQueryFactory(entityManager);
            QProduct qProduct = QProduct.product;

            List<Product> productList = jpqQueryFactory.selectFrom(qProduct)
                    .where(qProduct.name.eq("펜"))
                    .orderBy(qProduct.price.asc())
                    .fetch();


            for (Product product : productList) {
                System.out.println("-------------------");
                System.out.println();
                System.out.println("Product Number : " + product.getNumber());
                System.out.println("Product Name : " + product.getName());
                System.out.println("Product Price : " + product.getPrice());
                System.out.println("Product Stock : " + product.getStock());
                System.out.println();
                System.out.println("-------------------");
            }
        }
    //전체 조회 안 하고 싶으면,
                    /*
                    jpqQueryFactory.select.(qProduct.name)
                    .From(qProduct)
                    .where(qProduct.name.eq("펜"))
                    .orderBy(qProduct.price.asc())
                    .fetch();
                     */
    // Test 2 비교 위는 전체 검색
    @Test
    void queryDslTest3() {
        JPAQueryFactory jpqQueryFactory= new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;

        List<String> productList = jpqQueryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();


        for (String product : productList) {
            System.out.println("-------------------");
            System.out.println();
            System.out.println("Product Name: " + product);
            System.out.println();
            System.out.println("-------------------");
        }
        //select 여러개 검샘 Tuple  ,콤마로 구분
        List<Tuple> tupleList = jpqQueryFactory
                .select(qProduct.name, qProduct.price)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();// 배열타입으로 리턴 받는 것.

        for (Tuple product : tupleList) {
            System.out.println("-------------------");
            System.out.println("Product Name: " + product.get(qProduct.name));
            System.out.println("Product Name: " + product.get(qProduct.price));
            System.out.println("-------------------");
        }
    }
    @Test
    void queryDslTest4() {
        QProduct qProduct = QProduct.product;

        List<String> productList = jpaQueryFactory
                .select(qProduct.name)
                .from(qProduct)
                .where(qProduct.name.eq("펜"))
                .orderBy(qProduct.price.asc())
                .fetch();



        for (String product : productList) {
            System.out.println("-------------------");
            System.out.println();
            System.out.println("Product Name : " + product);
            System.out.println();
            System.out.println("-------------------");
        }
    }
    @DisplayName("자동 생성 및 수정 시간 업데이트 테스트")
    @Test
    public void auditingTest() {
        Product product = new Product();
        product.setName("펜");
        product.setPrice(1000);
        product.setStock(10000);

        Product savedProduct = productRepository.save(product);

        System.out.println("프로덕트 네임" +savedProduct.getName() );
        System.out.println("만든시간 테스트" +savedProduct.getCreatedAt() );
    }
}
