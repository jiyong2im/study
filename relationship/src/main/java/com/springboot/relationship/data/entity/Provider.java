package com.springboot.relationship.data.entity;

import lombok.*;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "provider")
public class Provider extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    //fetch EAGER : 기본이 Lazy 이기 때문에 즉시 로딩으로 변경 한 것. // 앞 테스트를 위해서 설정한 것 앞에는 지연 로딩하면  no Session 오류 뜸
//    @OneToMany(mappedBy = "provider", fetch = FetchType.EAGER)
    //영속성 전이 타입 어노테이션
    //@OneToMany(mappedBy = "provider", cascade = CascadeType.PERSIST)
    //고아객체 만들기
    @OneToMany(mappedBy = "provider", cascade = CascadeType.PERSIST, orphanRemoval = true)

    @ToString.Exclude
    private List<Product> productList = new ArrayList<Product>();
    // 이렇게 하면 아무것도 안 넣는 느낌을 없앨 수 있음. 맽 밑줄 만 빼면 에러 뜨는데 맨 밑줄이         product1.setProvider(provider); 와 대응함
    // 이렇게 하면 자바 코드상에서 값도 사용 할 수있고 db에도 들어간다.
    public void addProductList(Product product){
        this.productList.add(product);
        product.setProvider(this);
    }

}
