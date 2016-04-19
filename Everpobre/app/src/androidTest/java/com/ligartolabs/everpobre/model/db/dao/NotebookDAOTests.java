package com.ligartolabs.everpobre.model.db.dao;

import android.test.AndroidTestCase;

import com.ligartolabs.everpobre.model.Notebook;

/**
 * Created by pmargom on 19/4/16.
 */
public class NotebookDAOTests extends AndroidTestCase {

    public void testCanInsertNoteBook() {

        Notebook notebook = new Notebook(1, "test notebook");

        NotebookDAO notebooksDAO = new NotebookDAO(getContext());
        long id = notebooksDAO.insert(notebook);

        assertTrue(id > 0);

    }

}
