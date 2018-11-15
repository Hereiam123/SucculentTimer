package com.example.briandemaio.succulenttimer;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Succulent.class}, version = 1, exportSchema = false)
public abstract class SucculentRoomDatabase extends RoomDatabase {
    public abstract SucculentDao succulentDao();
    private static SucculentRoomDatabase INSTANCE;

    public static SucculentRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SucculentRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SucculentRoomDatabase.class, "succulent_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
