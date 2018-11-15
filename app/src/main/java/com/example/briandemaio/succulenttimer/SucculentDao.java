package com.example.briandemaio.succulenttimer;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface SucculentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Succulent succulent);

    @Query("DELETE FROM succulent_table")
    void deleteAll();

    @Query("SELECT * from succulent_table ORDER BY succulent ASC")
    LiveData<List<Succulent>> getAllSucculents();
}


