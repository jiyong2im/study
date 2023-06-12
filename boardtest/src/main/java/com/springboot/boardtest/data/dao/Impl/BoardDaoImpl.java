package com.springboot.boardtest.data.dao.Impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.springboot.boardtest.data.dao.BoardDao;
import com.springboot.boardtest.data.dto.BoardDto;
import com.springboot.boardtest.data.dto.InsertDto;
import com.springboot.boardtest.data.entity.Board;
import com.springboot.boardtest.data.entity.QBoard;
import com.springboot.boardtest.data.repository.BoardRepository;
import com.springboot.boardtest.service.BoardService;
import com.springboot.boardtest.service.Impl.BoardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;


@Component
public class BoardDaoImpl implements BoardDao {

    private final BoardRepository repository;

    @Autowired
    private BoardDaoImpl (BoardRepository repository) {
        this.repository = repository;
    }


    public Page<Board> selectBoardList(int start) {

        PageRequest request = PageRequest.of((start -1),5, Sort.by(Sort.Order.desc("id")));

        Page<Board> board = repository.findAll(request);

        //        List<Board> boardList = repository.findAllBy(start);

//        List<Board> board = repository.findAllBy();
//        Page<Board> productPage = repository.findById(, PageRequest.of(1,5));
        return board;
    }
    public Long numRecodes(){
        Long count = repository.count();
        return count;
    }

    public void saveWrite(Board board) {
        repository.save(board);
    }
    public void reSaveWrite(InsertDto insertDto, Long num) {

            Board dd = repository.findById(num).get();
            dd.setName(insertDto.getWriter());
            dd.setText(insertDto.getContent());
            dd.setTitle(insertDto.getTitle());
            repository.save(dd);

    }

    public Board selectOne(Long num){
         Board dd = repository.findById(num).get();
         dd.setViews(dd.getViews()+1L);
         repository.save(dd);
        System.out.println(repository.findById(num).get());
        System.out.println(dd.getCreatedAt());

        return dd;
    }

    public void deleteOneBoard(Long num){
        repository.deleteById(num);
    }


}
