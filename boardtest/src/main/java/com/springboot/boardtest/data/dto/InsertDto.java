package com.springboot.boardtest.data.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InsertDto {

    private String title;
    private String content;
    private String writer;

}
