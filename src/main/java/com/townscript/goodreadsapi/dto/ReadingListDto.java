package com.townscript.goodreadsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReadingListDto {
    private long bookId;
    private long userId;
    private String readingStatus;
}
