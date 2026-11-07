package com.example.ecomerceapp.local_database.database_classes;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.ecomerceapp.local_database.dao.CartDao;
import com.example.ecomerceapp.local_database.entity.CartEntity;

@Database(
        entities = {CartEntity.class},
        version = 1,
        exportSchema = false
)
public abstract class CartDB extends RoomDatabase {
    public abstract CartDao cartDao();
    private static volatile CartDB INSTANCE;
    public static CartDB getDatabase(Context context){
        if(INSTANCE== null)
            synchronized (CartDB.class){
                if(INSTANCE==null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            CartDB.class,
                            "cart_db"
                    ).build();
                }
            }
        return INSTANCE;
    }
}


