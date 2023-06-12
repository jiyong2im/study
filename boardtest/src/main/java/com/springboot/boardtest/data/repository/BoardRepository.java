package com.springboot.boardtest.data.repository;

import com.springboot.boardtest.data.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    boolean existsById(Long id);
    List<Board> findByIdOrderByIdDesc(Long Id);
    Page<Board> findById(Long id, Pageable pageable);
    @Override
    List<Board> findAll(Sort sort);

}
