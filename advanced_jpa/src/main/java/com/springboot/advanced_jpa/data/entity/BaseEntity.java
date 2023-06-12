package com.springboot.advanced_jpa.data.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/*
 JPA Auditing 기능에는 @CreatedBy, @ModifiedBy 어노테이션 존재 누가 엔티티 생성 수정 했는지 확인 가능 하지만 AuditorAware 스프링 빈 등록 요망
 */
@Getter@Setter@ToString
// JPA의 엔티티 클래스가 상속박을 경우 자식 클래스에게 매핑 정보 전달
@MappedSuperclass
// 엔티티 db 적용하기 전 후 콜백 요청 어노
//엔티티 auditing 정보 주입 리스너
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    // 데이터 생성 일자 자동 생성 어노
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
    //데이터 수정 날짜 자동 주입 어노
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
