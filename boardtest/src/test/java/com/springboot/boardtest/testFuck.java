package com.springboot.boardtest;

import com.springboot.boardtest.data.repository.BoardRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
public class testFuck {

    @Autowired
    BoardRepository boardRepository;

    @Test
    @DisplayName("페이징 테이스")
    void test() {
        PageRequest request = PageRequest.of(0,5);

        System.out.println(boardRepository.findAll(request).get());
    }

}
