package com.springboot.relationship.data.repository.support;

import com.springboot.relationship.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


// @Repository 어노테이션으로 원래 있던 Repository와 충돌이 일어나기 때문에 이름 지정을 해줘야 한다.
@Repository("productRepositorySupport")
public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {

}
