package com.springboot.relationship.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name ="category")
public class Category extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private List<Product> products = new ArrayList<>();
}
