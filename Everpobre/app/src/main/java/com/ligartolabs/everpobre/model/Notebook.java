package com.ligartolabs.everpobre.model;

/**
 * Created by pmargom on 18/4/16.
 */
public class Notebook {

    private int id;

    public Notebook(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}
