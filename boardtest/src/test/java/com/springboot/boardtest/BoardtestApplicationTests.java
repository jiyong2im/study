package com.springboot.boardtest;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.boardtest.data.dto.BoardDto;
import com.springboot.boardtest.data.entity.Board;
import com.springboot.boardtest.data.entity.QBoard;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
class BoardtestApplicationTests {
    // Autowired 는 리턴 받는 타입으로 결정 ㅚㅁ
    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @PersistenceContext
    EntityManager entityManager;
    @Test
    void contextLoads() {
    }
    @Test
    @DisplayName("querydsl")
    public void test() {
        JPAQuery<Board> query = new JPAQuery(entityManager);
        QBoard qBoard =  QBoard.board;

        List<Board> boardList = query
                .from(qBoard)
                .where(qBoard.name.eq("aa"))
                .orderBy(qBoard.id.desc())
                .fetch();
        //fetch return List, fetchOne Entity, fetchFirst return Board // 1건 내부 limit, fetchOne호출
        //fetchCount return Long
        // fetchResults() ? 리트스와 개수를 포함한 QueryResult 반환


        for (Board board : boardList){
            System.out.println("------------------");
            System.out.println();
            System.out.println("Board id"+ board.getId());
            System.out.println("Board Name"+ board.getName());
            System.out.println("Board Title"+ board.getTitle());
            System.out.println("Board Text"+ board.getText());
            System.out.println();
            System.out.println("------------------");
        }
    }

    @Test
    @DisplayName("querydsl JPAQueryFactory 활용방안")
    void test2() {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QBoard qBoard = QBoard.board;

        List<Board> boardList = jpaQueryFactory.selectFrom(qBoard)
                .where(qBoard.name.eq("aa"))
                .orderBy(qBoard.id.desc())
                .fetch();
        for (Board board : boardList){
            System.out.println("------------------");
            System.out.println();
            System.out.println("Board id    :"+ board.getId());
            System.out.println("Board Name  :"+ board.getName());
            System.out.println("Board Title :"+ board.getTitle());
            System.out.println("Board Text  :"+ board.getText());
            System.out.println();
            System.out.println("------------------");
        }

    }
    @Test
    @DisplayName("querydsl JPAQueryFactory 활용방안")
    void test3() {
        QBoard qBoard = QBoard.board;

        List<String> boardList = jpaQueryFactory.
                select(qBoard.name)
                .from(qBoard)
                .where(qBoard.name.eq("aa"))
                .orderBy(qBoard.id.desc())
                .fetch();

        for (String board : boardList){
            System.out.println("------------------");
            System.out.println();
            System.out.println(board);
            System.out.println();
            System.out.println("------------------");
        }
    }

    @Test
    @DisplayName("builder patten")
    void test4 () {

        BoardDto dto = BoardDto.builder().id(1L).title("지용").text("하이").build();
        System.out.println(dto.getId());
        System.out.println(dto.getTitle());
        System.out.println(dto.getText());

        /*
            private Long id;

    private String name;

    private String title;

    private String text;
    private Long views;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

         */
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        BoardDto dto2 = new BoardDto(2L, "선종", "타이틀", "텍스트", 4L );
        System.out.println(dto2.getId());
        System.out.println(dto2.getName());
        System.out.println(dto2.getTitle());
        System.out.println(dto2.getText());
        System.out.println(dto2.getViews());
    }
}
