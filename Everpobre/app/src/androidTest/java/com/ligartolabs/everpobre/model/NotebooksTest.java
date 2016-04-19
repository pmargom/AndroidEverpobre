package com.ligartolabs.everpobre.model;

import android.test.AndroidTestCase;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by pmargom on 19/4/16.
 */
public class NotebooksTest extends AndroidTestCase {

    private  Notebooks notebooks;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        Notebook n1 = new Notebook(1, "n1");
        Notebook n2 = new Notebook(2, "n2");

        Notebook[] arrayNotebooks = { n1, n2 };

        List<Notebook> notebooksList  = Arrays.asList(arrayNotebooks);

        notebooks = Notebooks.createNotebooks(notebooksList);
    }

    @Test
    public void testCreateNotebooks() throws Exception {
        assertNotNull(notebooks);
    }

    @Test
    public void testSize() throws Exception {
        assertEquals(notebooks.size(), 2);
    }

    @Test
    public void testGet() throws Exception {
        assertEquals(notebooks.get(0).getName(), "n1");
    }

    @Test
    public void testRemove() throws Exception {

    }

    @Test
    public void testAdd() throws Exception {

    }

    @Test
    public void testGetNotebooks() throws Exception {

    }
}