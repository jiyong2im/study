package com.springboot.boardtest.service;

import com.springboot.boardtest.data.dto.CrawlingDto;

import java.util.List;

public interface CrawlingService {
    public List<CrawlingDto> seongnamBoardCrawling();
    public List<CrawlingDto> geumgwangBoardCrawling();
}
