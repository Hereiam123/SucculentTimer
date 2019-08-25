package com.briandemaio.succulenttimer;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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


