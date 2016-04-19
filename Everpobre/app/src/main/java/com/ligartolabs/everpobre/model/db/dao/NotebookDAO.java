package com.ligartolabs.everpobre.model.db.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.ligartolabs.everpobre.model.Notebook;
import com.ligartolabs.everpobre.model.db.DBHelper;

import java.lang.ref.WeakReference;

//DAO, UNA CLASE POR TABLA

public class NotebookDAO {
	
	public static final String TABLE_NOTEBOOK = "NOTEBOOK";

	// Table field constants 
	public static final String KEY_NOTEBOOK_ID = "_id";
	public static final String KEY_NOTEBOOK_NAME = "name";
	//public static final String KEY_NOTEBOOK_DESCRIPTION = "description";

	public static final String SQL_CREATE_NOTEBOOK_TABLE =
			"create table " + TABLE_NOTEBOOK
					+ "( "
					+ KEY_NOTEBOOK_ID + " integer primary key autoincrement, "
					+ KEY_NOTEBOOK_NAME + " text not null"
					+ ");";


	public static final String[] allColumns = {
			KEY_NOTEBOOK_ID,
			KEY_NOTEBOOK_NAME,
			//KEY_NOTEBOOK_DESCRIPTION
	};
	private static final long INVALID_ID_DELETE_ALL_RECORDS = 0;
	private final String databaseName;

	private WeakReference<Context> context;
	// private Context context;

	public NotebookDAO(Context context) {
		this("Notebooks.sqlite", context);
	}

	public NotebookDAO(String databaseName, Context context) {
		this.context = new WeakReference<>(context);
		this.databaseName = databaseName;
	}

	public long insert(Notebook notebook) {
		if (notebook == null) {
			throw new IllegalArgumentException("Passing NULL notebook, imbecile");
		}

		if (context.get() == null) {
			throw new IllegalStateException("Context NULL");
		}

		// insert
		DBHelper db = DBHelper.getInstance(this.databaseName, context.get());

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

		if (context.get() == null) {
			throw new IllegalStateException("Context NULL");
		}

		DBHelper db = DBHelper.getInstance(this.databaseName, context.get());

		int numberOfRowsUpdated = db.getWritableDatabase().update(TABLE_NOTEBOOK, this.getContentValues(notebook), KEY_NOTEBOOK_ID + "=?", new String[]{"" + id});

		db.close();
		db = null;
		return numberOfRowsUpdated;
	}

	public void delete(long id) {
		DBHelper db = DBHelper.getInstance(this.databaseName, context.get());

		if (id == INVALID_ID_DELETE_ALL_RECORDS) {
			db.getWritableDatabase().delete(TABLE_NOTEBOOK,  null, null);
		} else {
			db.getWritableDatabase().delete(TABLE_NOTEBOOK, KEY_NOTEBOOK_ID + " = " + id, null);
		}
		db.close();
		db=null;
	}

	public void deleteAll() {
		delete(INVALID_ID_DELETE_ALL_RECORDS);
	}

	public static ContentValues getContentValues(Notebook notebook) {
		ContentValues content = new ContentValues();
		content.put(KEY_NOTEBOOK_NAME, notebook.getName());
		//content.put(KEY_NOTEBOOK_DESCRIPTION, notebook.getDescription());

		return content;
	}


	// convenience method

	public static Notebook notebookFromCursor(Cursor c) {
		assert c != null;

		String name = c.getString(c.getColumnIndex(KEY_NOTEBOOK_NAME));
		//String description = c.getString(c.getColumnIndex(KEY_NOTEBOOK_DESCRIPTION));
		long id = c.getLong(c.getColumnIndex(KEY_NOTEBOOK_ID));

		Notebook notebook = new Notebook(id, name);
		notebook.setId(id);

		return notebook;
	}


	/**
	 * Returns all notebooks in DB inside a Cursor
	 * @return cursor with all records
	 */

/*
	public Cursor queryCursor() {
		// select
		DBHelper db = DBHelper.getInstance(this.databaseName, context.get());

		Cursor c = db.getReadableDatabase().query(TABLE_NOTEBOOK, allColumns, null, null, null, null, null);

		return c;
	}

	public Notebooks query() {
		Notebooks notebooks = new Notebooks();

		Cursor cursor = queryCursor();
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			do {
				Notebook notebook = notebookFromCursor(cursor);
				notebooks.add(notebook);
			} while (cursor.moveToNext());
		}
		return notebooks;
	}
*/

	/**
	 * Returns a Notebook object from its id
	 * @param id - the notebook id in db
	 * @return Notebook object if found, null otherwise
	 */

/*
	public Notebook query(long id) {
		Notebook notebook = null;

		DBHelper db = DBHelper.getInstance(this.databaseName, context.get());

		String where = KEY_NOTEBOOK_ID + "=" + id;
		Cursor c = db.getReadableDatabase().query(TABLE_NOTEBOOK, allColumns, where, null, null, null, null);
		if (c != null) {
			if (c.getCount() > 0) {
				c.moveToFirst();
				notebook = notebookFromCursor(c);
			}
		}
		c.close();
		db.close();
		return notebook;
	}
*/

}
