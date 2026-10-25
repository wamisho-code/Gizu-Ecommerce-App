package com.example.ecomerceapp.local_database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
@Entity(tableName = "search_suggestions")
public class SearchKeyWords {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String word;

    @ColumnInfo(name = "search_count")
    private int searchCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(int searchCount) {
        this.searchCount = searchCount;
    }

    // Keep your existing constructors, getters, and setters.
}
