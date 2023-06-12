package com.springboot.boardtest.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/*
    querydslPredicateExecutor, QuerydslRepositorySupport 는 아직 안 함
 */
@Configuration
public class QueryDSLConfiguration {
    @PersistenceContext
    EntityManager entityManager;

    @Bean
    public JPAQueryFactory QueryDSLConfiguration () {
        return new JPAQueryFactory(entityManager);
    }
}
