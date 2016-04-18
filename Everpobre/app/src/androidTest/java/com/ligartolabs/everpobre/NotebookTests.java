package com.ligartolabs.everpobre;

import android.test.AndroidTestCase;

import com.ligartolabs.everpobre.model.Notebook;

/**
 * Created by pmargom on 18/4/16.
 */
public class NotebookTests extends AndroidTestCase{

    public static final String NOTEBOOK_TITLE = "Notebook title";
    public static final int NOTEBOOK_ID = 1;

    // test unitario
    public void testCanCreateANotebook() {
        final Notebook sut = new Notebook(NOTEBOOK_ID, NOTEBOOK_TITLE); // sut: system under test

        assertNotNull(sut);
        assertEquals(1, sut.getId());
        assertEquals(NOTEBOOK_TITLE, sut.getName());

    }

    public void testCreatingANotebookwithEmptyOrNullNameSetsDefaultName() {
        final Notebook sut = new Notebook(NOTEBOOK_ID, null); // con final, la hago no reutilizable, así evitamos equivocarnos al usar variables
        assertEquals(Notebook.DEFAULT_NAME, sut.getName());

        final Notebook sut2 = new Notebook(NOTEBOOK_ID, "");
        assertEquals(Notebook.DEFAULT_NAME, sut2.getName());
    }

}
