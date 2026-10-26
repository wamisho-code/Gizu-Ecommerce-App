package com.example.ecomerceapp.local_database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.example.ecomerceapp.local_database.entity.SearchKeyWords;

import java.util.List;

@Dao
public interface SearchKeyWordsDao {

    @Query("SELECT * FROM search_suggestions " +
            "WHERE LOWER(word) LIKE LOWER(:query) || '%' " +
            "ORDER BY CASE WHEN LOWER(word) = LOWER(:query) THEN 0 ELSE 1 END, " +
            "search_count DESC, word ASC LIMIT 10")
    LiveData<List<SearchKeyWords>> getSuggestedKeys(String query);


}
