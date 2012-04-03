package co.jp.kobashin.mapmarks.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BookmarkDbHelper extends SQLiteOpenHelper {
	private static String DB_NAME = "bookmark.db";
	private static int DB_VERSION = 1;
	private String CREATE_TABLE = "CREATE TABLE bookmark_records ( _id INTEGER PRIMARY KEY AUTOINCREMENT, url TEXT NOT NULL, name TEXT, category TEXT NOT NULL, date TEXT NOT NULL);";

	public BookmarkDbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	private BookmarkDbHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
