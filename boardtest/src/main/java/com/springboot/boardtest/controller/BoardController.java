package com.springboot.boardtest.controller;

import com.springboot.boardtest.config.auth.SessionUser;
import com.springboot.boardtest.data.dto.BoardDto;
import com.springboot.boardtest.data.dto.InsertDto;
import com.springboot.boardtest.data.entity.Board;
import com.springboot.boardtest.service.BoardService;
import com.springboot.boardtest.service.CrawlingService;
import com.springboot.boardtest.service.Impl.BoardServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

/*
    HttpServletRequest request 이전 값을 뽑을 수 있음
    request.getParameter("id")

    @RequestParam(value="id", required="false", defaultValue="mangdo")  required false면 디폴트 값 설정가능
    id

    @ModelAttribute("user") UserVO user // 자바 객체로 매핑 UserVO 에는 세터 메소드가 있어야만 한다.

    @RequestBody UserVO user // Json, XML 형식의 파일을 자바 객체로 변환 세터 메소드 따로 필요하지 않다.

    ** 어노테이션 생략 시 Stirng, Long은 RequestParam 으로 처리 이외의 것은 ModelAttribute 다


    @PosMapping("/getInfo/{id}");
    public void getInfo(@PathVariable String id){
    log.info("get parameter" + id);
 */

/*
    Model model
    model.addAttribute() 키 벨류 view로 넘기기

    ModelAndView dd
    dd.addObject() 키벨류
    dd.setViewName("") html이름 쓰기 애는 리턴 스트링이 아니라 자기 클래스

 */
@RequestMapping(value = "/", method = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@Controller
public class BoardController {
    private final BoardService boardService;
    private final CrawlingService crawlingService;
    private final Logger LOGGER = LoggerFactory.getLogger(BoardController.class);

    private final HttpSession httpSession;


    public BoardController(BoardService boardService, CrawlingService crawlingService, HttpSession httpSession){
        this.boardService = boardService;
        this.crawlingService = crawlingService;
        this.httpSession = httpSession;
    }


    @GetMapping({"/", "/list"})
    public String list(Model model, @RequestParam(value="num", required = false, defaultValue="1") Long num) {
//        Long pageNo = (num != null && num.longValue() > 0) ? num : 1;
        Long pageNo = num;
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null) {
            model.addAttribute("userName", user.getName());
        }

        model.addAttribute("page", boardService.pagination((pageNo.intValue())));
        model.addAttribute("list", boardService.selectList(pageNo.intValue()));

        return "list";
    }

    @PostMapping("/insertion")
    public String setInsert( InsertDto insert) {
        boardService.writeService(insert);
        System.out.println(insert.toString());

        return "redirect:list";
    }

    @PostMapping("/insertionre")
    public String setInsertRe( InsertDto insert, @RequestParam Long num) {
        boardService.reWriteService(insert ,num);

        return "redirect:list";
    }


    @GetMapping("/view")
    public String getView (@ModelAttribute("num") Long num, Model model){
        model.addAttribute("one", boardService.selectOne(num));
        return "view";
    }

    @GetMapping("write")
    public String setWrite (){
        return "write";
    }

    @PutMapping("rewrite")
    public String setReWrite (@ModelAttribute("num") Long num, Model model){
        model.addAttribute("one", boardService.selectOne(num));
        return "rewrite";
    }

    @DeleteMapping("deletion")
    public String delete(@ModelAttribute("num") Long num) {
        boardService.deleteOne(num);

        return "redirect:list";
    }

    @GetMapping("seongnamboard")
    public String getCrawling(Model model) {
        model.addAttribute("seongnamList", crawlingService.seongnamBoardCrawling());
        model.addAttribute("geumgwangList", crawlingService.geumgwangBoardCrawling());

        return "crawling";
    }




}
