package com.ligartolabs.everpobre.model.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.ligartolabs.everpobre.model.Notebook;
import com.ligartolabs.everpobre.model.Notebooks;
import com.ligartolabs.everpobre.model.db.DBConstants;
import com.ligartolabs.everpobre.model.db.DBHelper;

import java.util.LinkedList;
import java.util.List;

//DAO, UNA CLASE POR TABLA

public class NotebookDAO {
	
	public static final String TABLE_NOTEBOOK = "NOTEBOOK";

	private static final long INVALID_ID_DELETE_ALL_RECORDS = 0;
	private DBHelper db;

	public NotebookDAO() {
		
		db = DBHelper.getInstance();
	}

	public long insert(Notebook notebook) {
		if (notebook == null) {
			throw new IllegalArgumentException("Passing NULL notebook, imbecile");
		}

		// insert
		long id = db.getWritableDatabase().insert(TABLE_NOTEBOOK, null, this.getContentValues(notebook));
		notebook.setId(id);
		db.close();
		db = null;

		return id;
	}

	public int update(long id, Notebook notebook) {
		if (notebook == null) {
			throw new IllegalArgumentException("Passing NULL notebook, imbecile");
		}

		if (id < 1) {
			throw new IllegalArgumentException("Passing id invalid, imbecile");
		}

		int numberOfRowsUpdated = db.getWritableDatabase().update(TABLE_NOTEBOOK, this.getContentValues(notebook), DBConstants.KEY_NOTEBOOK_ID + "=?", new String[]{"" + id});

		db.close();
		db = null;
		return numberOfRowsUpdated;
	}

	public void delete(long id) {

		if (id == INVALID_ID_DELETE_ALL_RECORDS) {
			db.getWritableDatabase().delete(TABLE_NOTEBOOK,  null, null);
		} else {
			db.getWritableDatabase().delete(TABLE_NOTEBOOK, DBConstants.KEY_NOTEBOOK_ID + " = " + id, null);
		}
		db.close();
		db=null;
	}

	public void deleteAll() {
		delete(INVALID_ID_DELETE_ALL_RECORDS);
	}

	public static ContentValues getContentValues(Notebook notebook) {
		ContentValues content = new ContentValues();
		content.put(DBConstants.KEY_NOTEBOOK_NAME, notebook.getName());
		//content.put(KEY_NOTEBOOK_DESCRIPTION, notebook.getDescription());

		return content;
	}


	// convenience method
	public static @NonNull Notebook notebookFromCursor(final @NonNull Cursor c) {
		assert c != null;

		final String name = c.getString(c.getColumnIndex(DBConstants.KEY_NOTEBOOK_NAME));
		final long id = c.getLong(c.getColumnIndex(DBConstants.KEY_NOTEBOOK_ID));

		final Notebook notebook = new Notebook(id, name);
		notebook.setId(id);

		return notebook;
	}


	/**
	 * Returns all notebooks in DB inside a Cursor
	 * @return cursor with all records
	 */
	public Cursor queryCursor() {
		// select
		Cursor c = db.getReadableDatabase().query(TABLE_NOTEBOOK, DBConstants.allColumns, null, null, null, null, null);

		return c;
	}

	public Notebooks query() {
		List<Notebook> notebookList = new LinkedList<>();

		Cursor cursor = queryCursor();
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			do {
				Notebook notebook = notebookFromCursor(cursor);
				notebookList.add(notebook);
			} while (cursor.moveToNext());
		}
		Notebooks notebooks = Notebooks.createNotebooks(notebookList);

		return notebooks;
	}

	/**
	 * Returns a Notebook object from its id
	 * @param id - the notebook id in db
	 * @return Notebook object if found, null otherwise
	 */
	public @Nullable Notebook query(long id) {
		Notebook notebook = null;

		String where = DBConstants.KEY_NOTEBOOK_ID + "=" + id;
		Cursor c = db.getReadableDatabase().query(TABLE_NOTEBOOK, DBConstants.allColumns, where, null, null, null, null);
		if (c != null) {
			if (c.getCount() > 0) {
				c.moveToFirst();
				notebook = notebookFromCursor(c);
			}
			c.close();
		}

		db.close();
		return notebook;
	}

}
