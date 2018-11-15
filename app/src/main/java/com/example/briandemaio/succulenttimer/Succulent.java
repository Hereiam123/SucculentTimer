package com.example.briandemaio.succulenttimer;

public class Succulent {
    private final int name;
    private int imageResource;

    public Succulent(int name, int imageResource) {
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
