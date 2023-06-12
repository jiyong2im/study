package com.springboot.boardtest.data.dao;

import com.springboot.boardtest.data.dto.InsertDto;
import com.springboot.boardtest.data.entity.Board;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BoardDao {
    Page<Board> selectBoardList(int start);

    void saveWrite(Board board);

    void reSaveWrite(InsertDto insertDto, Long num);

    Long numRecodes();
    Board selectOne(Long num);

    void deleteOneBoard(Long num);


}
