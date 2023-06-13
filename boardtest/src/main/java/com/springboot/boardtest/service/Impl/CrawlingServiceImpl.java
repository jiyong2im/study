package com.springboot.boardtest.service.Impl;

import com.springboot.boardtest.data.dao.BoardDao;
import com.springboot.boardtest.data.dto.CrawlingDto;
import com.springboot.boardtest.service.CrawlingService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//            try {
//                Document doc = Jsoup.connect(URL).get();
//                Elements elements = doc.select(".title");
//
//
//                for (Element element : elements) {
//                    craList.add(element.text());
//                }
//
//            } catch (Exception e) {
//                System.out.println("크롤링 실패 : " + e);
//            }
@Service
public class CrawlingServiceImpl implements CrawlingService {
    private final Logger LOGGER = LoggerFactory.getLogger(CrawlingServiceImpl.class);

    public List<CrawlingDto> seongnamBoardCrawling() {
        List<CrawlingDto> crawlingDtoList = new ArrayList<>();
        //URL형식이 #777777임
        String searchURL = "https://www.seongnam.go.kr/city/1000052/30001/bbsView.do?currentPage=1&searchSelect=title&searchWord=&searchOrganDeptCd=&searchCategory=&subTabIdx=&idx=";


        for (int i = 1; i < 5; i++) {
            String URL = "https://www.seongnam.go.kr/city/1000052/30001/bbsList.do?currentPage=" + i + "&idx=&searchCategory=&searchSelect=title&searchWord=";

            try {
                Document doc = Jsoup.connect(URL).get();
                //성남시청 게시글 제목 클래스 elements에 넣어주기
                Elements elements = doc.getElementsByClass("title");

                //elements 안에 있는 배열 형식 div 태그 안에 있는 a 태크 빼기
                for (Element element : elements) {

                    Elements anchorTags = element.select("a");
                    System.out.println(anchorTags.text());

                    for (Element anchorTag : anchorTags) {
                        CrawlingDto dto = new CrawlingDto();
                        String href = anchorTag.attr("href");
                        // ㄷㄷ 착각하고 href.substring(1); 계속 이러고 있었네
                        String realHref=  href.substring(1);

                        dto.setLink(searchURL + realHref);
                        dto.setTitle(anchorTag.text());
                        crawlingDtoList.add(dto);
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
                }
            }

            return crawlingDtoList;
        }



    public List<CrawlingDto> geumgwangBoardCrawling() {
        List<CrawlingDto> crawlingDtoList = new ArrayList<>();
        String searchURL = "https://www.jungwongu.go.kr:10008/dong_office/mobile/sub/content.asp";

        https://www.jungwongu.go.kr:10008/dong_office/mobile/sub/content.asp

        for (int i = 1; i < 5; i++) {
            String URL = "https://www.jungwongu.go.kr:10008/dong_office/mobile/sub/content.asp?cIdx=134&fboard=board_dong11&fpage=" + i + " &searchOpt1=&searchName=&orderby1=b_ref&orderby2=Desc&intPageSize=10";



            try {
                Document doc = Jsoup.connect(URL).get();

                Elements elements = doc.getElementsByClass("text-left title");

                for (Element element : elements) {

                    Elements anchorTags = element.select("a");
                    System.out.println(anchorTags.text());

                    for (Element anchorTag : anchorTags) {
                        CrawlingDto dto = new CrawlingDto();
                        String href = anchorTag.attr("href");

                        dto.setLink(searchURL+href);
                        dto.setTitle(anchorTag.text());
                        crawlingDtoList.add(dto);
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }

        return crawlingDtoList;
    }

}