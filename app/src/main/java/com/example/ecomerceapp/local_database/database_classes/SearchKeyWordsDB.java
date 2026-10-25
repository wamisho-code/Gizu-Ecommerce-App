package com.example.ecomerceapp.local_database.database_classes;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.ecomerceapp.local_database.dao.SearchKeyWordsDao;
import com.example.ecomerceapp.local_database.entity.SearchKeyWords;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(
        entities = {SearchKeyWords.class},
        version = 1,
        exportSchema = false

)
public abstract class  SearchKeyWordsDB extends RoomDatabase {
    public abstract SearchKeyWordsDao searchKeyWordsDao ();
    public static  final ExecutorService writeExecutor = Executors.newSingleThreadExecutor();
    private static volatile SearchKeyWordsDB INSTANCE;
    public static SearchKeyWordsDB get(Context context){
        if(INSTANCE==null){
            synchronized (SearchKeyWordsDB.class){
                if(INSTANCE==null){
                    INSTANCE = Room.databaseBuilder(
                            context,
                            SearchKeyWordsDB.class,
                            "gizu_local_db"
                    ).createFromAsset("local_database/gizu_local_db.db")
                            .build();
                }

            }
        }
        return INSTANCE;
    }
}
