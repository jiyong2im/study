package com.springboot.boardtest.controller;

import com.springboot.boardtest.config.auth.SessionUser;
import com.springboot.boardtest.data.dto.BoardDto;
import com.springboot.boardtest.data.dto.InsertDto;
import com.springboot.boardtest.data.entity.Board;
import com.springboot.boardtest.service.BoardService;
import com.springboot.boardtest.service.CrawlingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("jiyong/spring/board/restful")
public class BoardRestController {
        private final BoardService boardService;
        private final CrawlingService crawlingService;
        private final Logger LOGGER = LoggerFactory.getLogger(BoardRestController.class);


        public BoardRestController(BoardService boardService, CrawlingService crawlingService){
            this.boardService = boardService;
            this.crawlingService = crawlingService;
        }

        @GetMapping("/list")
        public ResponseEntity<List<BoardDto>> getList(@RequestParam(value="num", required = false, defaultValue="1") Long num) {
            Long pageNo = num;
            List<BoardDto> boardList = boardService.selectList(pageNo.intValue());

            return ResponseEntity.ok(boardList);
        }

        @PostMapping("/list")
        public ResponseEntity<String> createBoard(@RequestBody InsertDto insert) {
            boardService.writeService(insert);
            return ResponseEntity.status(HttpStatus.CREATED).body("Insert successful");
        }

        @PostMapping("/list/{num}")
        public ResponseEntity<String> modifyBoard(@RequestBody InsertDto insert, @PathVariable("num") Long num) {
            boardService.reWriteService(insert, num);
            return ResponseEntity.status(HttpStatus.CREATED).body("Reinsert successful");
        }

        @GetMapping("/list/{num}")
        public ResponseEntity<BoardDto> getView(@PathVariable("num") Long num) {
            BoardDto board = boardService.selectOne(num);
            if (board != null) {
                return ResponseEntity.ok(board);
            } else {
                return ResponseEntity.notFound().build();
            }
        }

        @DeleteMapping("/list/{num}")
        public ResponseEntity<String> delete(@PathVariable("num") Long num) {
            boardService.deleteOne(num);
            return ResponseEntity.ok("Delete successful");
        }


    }

