package com.springboot.boardtest.service;

import com.springboot.boardtest.data.dto.BoardDto;
import com.springboot.boardtest.data.dto.InsertDto;
import com.springboot.boardtest.data.dto.Pagination;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public interface BoardService {

    List<BoardDto> selectList(int pageNo) ;

    ArrayList<Pagination> pagination(int pageNo);
    BoardDto selectOne(Long num);

    void writeService (InsertDto insertDto);

    void reWriteService (InsertDto insertDto, Long num);

    void deleteOne(Long num);


}
