package com.springboot.relationship.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class QueryConfiguration {

    @PersistenceContext
    EntityManager entityManager;
/*
    빈에 등록 해 놓고 autowired 하는 것.
    원래는
    EntityManager entityManager;
    JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
    이래야 함

 */

    @Bean
    public JPAQueryFactory jpaQueryFactory(){
        return new JPAQueryFactory(entityManager);
    }

}
