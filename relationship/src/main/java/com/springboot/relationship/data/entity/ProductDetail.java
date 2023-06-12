package com.springboot.relationship.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "product_detail")
@Getter@Setter@NoArgsConstructor@ToString(callSuper = true)@EqualsAndHashCode(callSuper = true)
public class ProductDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    // @OneToOne(optiona중 = false) : null 허용 안함
    @OneToOne
    // JOinColumn 속성
    // name : 매핑할 외래키 설정, referencedColumnName : 외래키가 창조할 상대 테이블의 칼럼명을 지정, foreignKey : 외래키 생성하면서 지정할 제약 조건 설정
    @JoinColumn(name = "product_number")
    private Product product;
}
