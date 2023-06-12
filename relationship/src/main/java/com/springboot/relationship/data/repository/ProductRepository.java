package com.springboot.relationship.data.repository;

import com.springboot.relationship.data.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/*
    findBY, readBy, getBy, queryBy, searchBy, streamBy

 */
public interface ProductRepository extends JpaRepository<Product, Long> {


    Optional<Product> findByNumber(Long number);
    List<Product> findAllByName(String name);
    Product queryByNumber(Long number);

    /// 특정 데이터 존재 유무 키워드
    boolean existsByNumber(Long number);

    //조회 쿼리 수행후 갯수 리턴
    long countByName(String name);

    //삭제 쿼리
    void deleteByNumber(Long number);
    long removeByNumber(Long number);

    // 조회된 쿼리 결괏값 개수 제한 키워드
    List<Product> findFirst5ByName(String name);
    List<Product> findTop10ByName(String name);

    // 값 일치 조건 키워드 Is == Equals
    Product findByNumberIs(Long number);
    Product findByNumberEquals(Long number);

    // Not 불일치 조건 Is 생략 가능
    Product findByNumberIsNot(Long number);
    Product findByNumberNot(Long number);

    // 값이 null 인지 확인 키워드
    List<Product> findByUpdatedAtNull();
    List<Product> findByUpdatedAtIsNull();
    List<Product> findByUpdatedAtNotNull();
    List<Product> findByUpdatedAtIsNotNull();



    // boolean 타입으로 지정된 칼럼값을 확인 하는 키워드   // 시발 책 잘 안 읽어보고 실제 코드에 반영하면 에러 발생 몰랐다 product 엔티티에 불린 타입사용하는거라서 오류
//    Product findByisActiveTrue();
//    Product findByisActiveIsTrue();
//    Product findByisActiveFalse();
//    Product findByisActiveIsFalse();





    // And, or 여러 조건을 묶을 때
    Product findByNumberAndName(Long number, String name);
    Product findByNumberOrName(Long number, String name);









    //datetime 칼럼을 대상으로 한 비교 연산에 사용할 수 있는 키워드 초과,미안 개념으로 연산 수행, 경겟값 포함하려면 Equal 이거 오류
    List<Product> findByPriceIsGreaterThan(Long price);
    List<Product> findByPriceGreaterThan(Long price);
    List<Product> findByPriceGreaterThanEqual(Long price);
    List<Product> findByPriceIsLessThan(Long price);
    List<Product> findByPriceLessThan(Long price);
    List<Product> findByPriceLessThanEqual(Long price);
    List<Product> findByPriceIsBetween(Long lowPrice, Long highPrice);
    List<Product> findByPriceBetween(Long lowPrice, Long highPrice);








    //
    List<Product> findByNameLike(String name);
    List<Product> findByNameIsLike(String name);

    List<Product> findByNameContains(String name);
    List<Product> findByNameContaining(String name);
    List<Product> findByNameIsContaining(String name);

    List<Product> findByNameStartsWith(String name);
    List<Product> findByNameStartingWith(String name);
    List<Product> findByNameIsStartingWith(String name);

    List<Product> findByNameEndsWith(String name);
    List<Product> findByNameEndingWith(String name);
    List<Product> findByNameIsEndingWith(String name);

    //    //정렬 구문 키워드
    List<Product> findByNameOrderByNumberAsc(String name);
    List<Product> findByNameOrderByNumberDesc(String name);
    // 정렬구문은 And, Or 사용 안하고 쓸 수 있다
    List<Product> findByNameOrderByPriceAscStockDesc(String name);

    //  위 코드는 가독성이 떨어짐
    List<Product> findByName(String name, Sort sort);

    // 페이징 처리
    Page<Product> findByName(String name, Pageable pageable);

    //JPQL

    //     ?1 < 1은 첫번째 파라메타 의미 AS 생략 가능
    @Query("SELECT p FROM Product AS p WHERE p.name = ?1")
    List<Product> findByName(String name);
    @Query("SELECT p FROM Product p WHERE p.name= :name")
    List<Product> findByNameParam(@Param("name") String name);

    //특정 칼럼만 추출하는 쿼리
    @Query("SELECT p.name, p.price, p.stock FROM Product p WHERE p.name = :name")
    List<Object[]> findByNameParam2(@Param("name") String name);


}
