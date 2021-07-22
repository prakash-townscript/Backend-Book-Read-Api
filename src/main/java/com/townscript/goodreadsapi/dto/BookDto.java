package com.townscript.goodreadsapi.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDto {

    private Long bookId;
    private String name;
    private String description;
    private String image;
    private String author;
    private int pages;
}
