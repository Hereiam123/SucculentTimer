package com.briandemaio.succulenttimer;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "succulent_table")
public class Succulent {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name = "succulent")
    private String name;

    @ColumnInfo(name = "expiryTime")
    private long expiryTime;

    private int imageResource;

    public Succulent(@NonNull String name, @NonNull int imageResource, @NonNull long expiryTime) {
        this.name = name;
        this.imageResource = imageResource;
        this.expiryTime = expiryTime;
    }

    @Ignore
    public Succulent(int id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }

    @Ignore
    public Succulent(@NonNull String name, @NonNull int imageResource) {
        this.name = name;
        this.imageResource = imageResource;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setExpiryTime(long expiryTime) {
        this.expiryTime = expiryTime;
    }

    public long getExpiryTime() {
        return expiryTime;
    }

    public String getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }
}
