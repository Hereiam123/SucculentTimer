package com.example.briandemaio.succulenttimer;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "succulent_table")
public class Succulent {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "succulent")
    private String name;

    private int imageResource;
    private int nameId;
    private int timeId;

    public void setNameId(int nameId) {
        this.nameId=nameId;
    }

    public Succulent(@NonNull String name, @NonNull int imageResource) {
        this.name = name;
        this.imageResource = imageResource;
    }

    @Ignore
    public Succulent(int id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }

    @Ignore
    public Succulent(int nameId, int imageResource) {
        this.nameId = nameId;
        this.imageResource = imageResource;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getTimeId(){return timeId;}

    public void setTimeId(int timeId) {
        this.timeId = timeId;
    }

    public int getId(){return id;}

    public String getName() {
        return name;
    }

    public int getNameId() {
        return nameId;
    }

    public int getImageResource() {
        return imageResource;
    }

}
