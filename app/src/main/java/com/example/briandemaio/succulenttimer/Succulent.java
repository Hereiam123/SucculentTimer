package com.example.briandemaio.succulenttimer;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "succulent_table")
public class Succulent {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "succulent")
    private String name;

    private int imageResource;
    private int nameId;

    public void setNameId(int nameId) {
        this.nameId=nameId;
    }

    public Succulent(@NonNull String name, @NonNull int imageResource) {
        this.name = name;
        this.imageResource = imageResource;
    }

    @Ignore
    public Succulent(int nameId, int imageResource) {
        this.nameId = nameId;
        this.imageResource = imageResource;
    }

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
