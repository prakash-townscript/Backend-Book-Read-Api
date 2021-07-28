package com.townscript.goodreadsapi.Repository;

import com.townscript.goodreadsapi.model.ReadingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReadingListRepository extends JpaRepository<ReadingList,Long> {

    @Query(value = "update reading_list " +
            "set reading_status = 'completed' " +
            "where book_id = :bookId and user_id = :userId",
            nativeQuery = true)
    @Modifying
    ReadingList updateUserReadingList(@Param("userId") Long userId,@Param("bookId") Long bookId);

    @Query(value = "delete from reading_list "+
            "where book_id = :bookId and user_id = :userId",
            nativeQuery = true)
    @Modifying
    ReadingList deleteReadingList(@Param("userId") Long userId,@Param("bookId") Long bookId);


}
