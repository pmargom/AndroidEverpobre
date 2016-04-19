package com.ligartolabs.everpobre.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by pmargom on 18/4/16.
 */
public class Notebooks {

    List<Notebook> notebooks;

    // factory static method
    public static Notebooks createNotebooks(List<Notebook> notebooks) {
        Notebooks myNotebooks = new Notebooks();

        for (Notebook n: notebooks) {
            myNotebooks.add(n);
        }

        return myNotebooks;
    }

    private Notebooks() {

    }

    public int size() {
        return getNotebooks().size();
    }

    public Notebook get(int index) {
        return getNotebooks().get(index);
    }

    public void remove(Notebook notebook) {
        getNotebooks().remove(notebook);
    }

    public void add(Notebook n) {
        getNotebooks().add(n); // no usamos variables de instancia por no ser ni el constructor ni fuera de los getters
    }

    // lazy getter
    public List<Notebook> getNotebooks() { // reservamos la memoria lo más tarde posible
        if (this.notebooks == null) {
            this.notebooks = new LinkedList<>();
        }
        return this.notebooks;
    }


}
