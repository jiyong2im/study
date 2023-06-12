package com.springboot.boardtest.service.Impl;

import com.springboot.boardtest.data.dao.BoardDao;
import com.springboot.boardtest.data.dto.BoardDto;
import com.springboot.boardtest.data.dto.InsertDto;
import com.springboot.boardtest.data.dto.Pagination;
import com.springboot.boardtest.data.entity.Board;
import com.springboot.boardtest.service.BoardService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
    private final BoardDao boardDao;
    private static final int paginationSize = 5;
    private static final int listSize = 5;

    private final Logger LOGGER = LoggerFactory.getLogger(BoardServiceImpl.class);

    @Autowired
    public BoardServiceImpl(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    public ArrayList<Pagination> pagination(int pageNo) {
        ArrayList<Pagination> pgnList = new ArrayList<>();

        Long numRecords = boardDao.numRecodes();
        // listSize =5 , Records =30
        // numPages = 6
        int numPages = (int)Math.ceil((double)numRecords / listSize);
        // pageNo = 2
        int firstLink = ((pageNo - 1) / paginationSize) * paginationSize + 1;
        // firstLink = 26
        int lastLink = firstLink + paginationSize - 1;
        //lastLink 30
        if (lastLink > numPages) {
            lastLink = numPages;
        }
        // lastLink = 6
        LOGGER.info("안녕하세요 친구들 {}", lastLink);
        if (firstLink > 1) {
            pgnList.add(new Pagination("이전", firstLink - 1, false));
        }

        for (int i = firstLink; i <= lastLink; i++) {
            pgnList.add(new Pagination("" + i, i, i == pageNo));
        }

        if (lastLink < numPages) {
            pgnList.add(new Pagination("다음", lastLink + 1, false));
        }

        return pgnList;
    }


    public List<BoardDto> selectList(int pageNo) {


        Page<Board> list = boardDao.selectBoardList(pageNo);

        List<BoardDto> boardList= new ArrayList<>();

        for(Board dd :list) {
            BoardDto dto = new BoardDto();
            dto.setId(dd.getId());
            dto.setName(dd.getName());
            dto.setTitle(dd.getTitle());
            dto.setViews(dd.getViews());
            dto.setText(dd.getText());
            dto.setCreatedAt(dd.getCreatedAt());
            dto.setUpdatedAt(dd.getUpdatedAt());
            dto.getViews();
            boardList.add(dto);

        }


        return boardList;
    }

    public void writeService(InsertDto insertDto) {
        Board board = new Board();
        board.setName(insertDto.getWriter());
        board.setText(insertDto.getContent());
        board.setTitle(insertDto.getTitle());
        board.setViews(0L);
        boardDao.saveWrite(board);
    }
    public void reWriteService(InsertDto insertDto, Long num){
        boardDao.reSaveWrite(insertDto, num);
    }
    public BoardDto selectOne(Long num) {

        Board board = boardDao.selectOne(num);
        BoardDto boardDtodto = new BoardDto();
        boardDtodto.setId(board.getId());
        boardDtodto.setName(board.getName());
        boardDtodto.setTitle(board.getTitle());
        boardDtodto.setViews(board.getViews());
        boardDtodto.setText(board.getText());
        boardDtodto.setCreatedAt(board.getCreatedAt());
        boardDtodto.setUpdatedAt(board.getUpdatedAt());
        return boardDtodto;
    }

    public void deleteOne(Long num) {
        boardDao.deleteOneBoard(num);
    }

    public List<String> crawling() {
        List<String> craList = new ArrayList<>();

        for (int i = 1; i < 5; i++) {
            String URL = "https://www.seongnam.go.kr/city/1000052/30001/bbsList.do?currentPage="+i+"&idx=&searchCategory=&searchSelect=title&searchWord=";
            try {
                Document doc = Jsoup.connect(URL).get();
                Elements elements = doc.select(".title");

                for (Element element : elements) {
                    craList.add(element.text());
                }

            } catch (Exception e) {
                System.out.println("크롤링 실패 : " + e);
            }
        }
        return craList;
    }



}
