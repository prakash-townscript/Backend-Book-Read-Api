package com.townscript.goodreadsapi.service;

import com.townscript.goodreadsapi.Repository.ReadingListRepository;
import com.townscript.goodreadsapi.dto.ReadingListDto;
import com.townscript.goodreadsapi.model.ReadingList;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;


@Service
@AllArgsConstructor
public class ReadingListService {

    private final ReadingListRepository readingListRepository;

    @Transactional
    public ReadingList updateUserReadingList(Long userId,Long bookId){
        return readingListRepository.updateUserReadingList(userId,bookId);
    }

    @Transactional
    public ReadingList deleteFromReadingList(Long userId, Long bookId){
        return readingListRepository.deleteReadingList(userId,bookId);
    }

    @Transactional
    public ReadingList save(ReadingListDto readingListDto){
        ReadingList readingList  = mapFromDto(readingListDto);
        ReadingList saved = readingListRepository.save(readingList);
        return saved;
    }


    private ReadingList mapFromDto(ReadingListDto readingListDto) {
        return ReadingList.builder()
                .userId(readingListDto.getUserId())
                .bookId(readingListDto.getBookId())
                .readingStatus(readingListDto.getReadingStatus())
                .build();
    }

    private ReadingListDto mapToDto(ReadingList readingList) {
        return ReadingListDto.builder()
                .userId(readingList.getUserId())
                .bookId(readingList.getBookId())
                .readingStatus(readingList.getReadingStatus())
                .build();
    }

}
