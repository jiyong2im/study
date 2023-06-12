package com.springboot.boardtest.data.dto;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {

    private Long id;

    private String name;

    private String title;

    private String text;
    private Long views;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
