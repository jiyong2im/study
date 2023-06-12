package com.springboot.boardtest.data.dto;

import lombok.*;

@Data
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class CrawlingDto {
    private String link;
    private String title;
    private String num;
}
