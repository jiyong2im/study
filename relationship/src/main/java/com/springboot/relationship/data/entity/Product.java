package com.springboot.relationship.data.entity;

//import jakarta.persistence.*;

import lombok.*;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
// 밑에 두개는 부모 클래스의 필드를 포함함
// 양방향 매핑 설정시 ToString 순환참조 발생 스탯오버플로우 오류 발생 %%
//@ToString(exclude = "name")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "product")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;
    // 양방향 매핑중..
    @OneToOne(mappedBy = "product")
    @ToString.Exclude // 양방향 매핑 이메소드 순환참조 오류 없애줌
    private ProductDetail productDetail;

    // 양방향 매핑중
    // 외래키를 갖는 쪽이 주인....
    @ManyToOne
    @JoinColumn(name = "provider_id")
    @ToString.Exclude
    private Provider provider;

    @ManyToMany
    @ToString.Exclude
    private List<Producer> producers = new ArrayList<>();

    public void addProducer(Producer producer) {
        this.producers.add(producer);
    }


}