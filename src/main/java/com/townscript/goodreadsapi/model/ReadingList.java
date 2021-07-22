package com.townscript.goodreadsapi.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@IdClass(ReadingListKey.class)
public class ReadingList {

    @Id
    private Long bookId;

    @Id
    private Long userId;

    private String readingStatus;
}
