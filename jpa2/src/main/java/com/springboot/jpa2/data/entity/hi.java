package com.springboot.jpa2.data.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
// refactor -> delombok -> all annotations
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "name")
@EqualsAndHashCode // 객체 동일, 내용 동일 메소드 생성해줌
//@RequiredArgsConstructor // 파이널이나 @NotNull이 설정된 변수를 매개변수로 갖는 생성자 생성

@Table
public class hi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
