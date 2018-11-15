package com.example.briandemaio.succulenttimer;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "succulent_table")
public class Succulent {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "succulent")
    private final int name;
    private int imageResource;

    public Succulent(@NonNull int name, @NonNull int imageResource) {
        this.name = name;
        this.imageResource = imageResource;
    }

    public int getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }

}
