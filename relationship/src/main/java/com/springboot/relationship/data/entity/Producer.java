package com.springboot.relationship.data.entity;

import lombok.*;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "producer")
public class Producer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    @ManyToMany
    @ToString.Exclude
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product){
        products.add(product);
    }
}
