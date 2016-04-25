package com.ligartolabs.everpobre.model.db;

import com.ligartolabs.everpobre.model.db.dao.NotebookDAO;

/**
 * Created by pmargom on 25/4/16.
 */
public class DBConstants {
	// Table field constants
	public static final String KEY_NOTEBOOK_ID = "_id";
	public static final String KEY_NOTEBOOK_NAME = "name";
	public static final String[] allColumns = {
			KEY_NOTEBOOK_ID,
			KEY_NOTEBOOK_NAME,
			//KEY_NOTEBOOK_DESCRIPTION
	};
	public static final String SQL_CREATE_NOTEBOOK_TABLE =
			"create table " + NotebookDAO.TABLE_NOTEBOOK
					+ "( "
					+ KEY_NOTEBOOK_ID + " integer primary key autoincrement, "
					+ KEY_NOTEBOOK_NAME + " text not null"
					+ ");";
	public static final String DBNAME = "Everpobre.sqlite";
}
