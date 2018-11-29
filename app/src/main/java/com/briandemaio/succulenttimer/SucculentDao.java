package com.briandemaio.succulenttimer;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface SucculentDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(Succulent succulent);

    @Update
    void update(Succulent... succulent);

    @Delete
    void deleteSucculent(Succulent succulent);

    @Query("SELECT * from succulent_table ORDER BY succulent ASC")
    LiveData<List<Succulent>> getAllSucculents();
}


