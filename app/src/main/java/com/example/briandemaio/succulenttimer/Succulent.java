package com.example.briandemaio.succulenttimer;

public class Succulent {
    private final int name;
    private final int imageResource;
    private final String imageUrl;

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

    public String getImageUrl() {
        return imageUrl;
    }
}
