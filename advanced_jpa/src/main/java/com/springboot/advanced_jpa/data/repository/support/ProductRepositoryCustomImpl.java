package com.springboot.advanced_jpa.data.repository.support;

import com.springboot.advanced_jpa.data.entity.Product;
import com.springboot.advanced_jpa.data.entity.QProduct;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
//
public class ProductRepositoryCustomImpl extends QuerydslRepositorySupport implements ProductRepositoryCustom {
    // 엄마한테 Product.class 가 엔티티라고 알려줘야함  엄마는 extends 받은 QuerydslRepositorySupport
    public ProductRepositoryCustomImpl(){
        super(Product.class);
    }

    @Override
    public List<Product> findByName(String name) {
        QProduct qProduct = QProduct.product;
        List<Product> productList = from(qProduct)
                .where(qProduct.name.eq(name))
                .select(qProduct)
                .fetch();
        return productList;
    }
}
