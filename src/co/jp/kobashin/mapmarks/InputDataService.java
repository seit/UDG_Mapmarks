package co.jp.kobashin.mapmarks;

import android.app.IntentService;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;
import co.jp.kobashin.mapmarks.db.BookmarkDbHelper;
import co.jp.kobashin.mapmarks.util.MapMarksUtil;

public class InputDataService extends IntentService {
	private static final String SERVICE_NAME = "InputDataService";

	public InputDataService() {
		super(SERVICE_NAME);
	}

	public InputDataService(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	/**
	 * ServiceTask IntentのgetExtraで処理を振り分ける
	 * 
	 */
	@Override
	protected void onHandleIntent(Intent intent) {

		int cmd = intent.getIntExtra(MapMarksUtil.CMD_ID,
				MapMarksUtil.CMD_ERROR);
		switch (cmd) {
		case MapMarksUtil.CMD_SAVE:
			String[] val = intent.getStringArrayExtra(MapMarksUtil.CMD_VAL);
			if (val != null) {
				saveUrl(val[0], val[1], val[2]);
			} else {
				Toast.makeText(this, "MapMarkの書き込みに失敗しました", Toast.LENGTH_LONG)
						.show();
			}
			break;
		default:
			// error case
		}
	}

	/**
	 * databaseにinsertをかける
	 * 
	 * @param u
	 * @param category
	 * @param name
	 */
	private void saveUrl(String u, String category, String name) {
		BookmarkDbHelper helper = new BookmarkDbHelper(this);
		SQLiteDatabase db = helper.getWritableDatabase();

		if (name == null) {
			name = "NULL";
		}

		String insert = String.format(MapMarksUtil.INSERT_COLUMN, u, category,
				name);
		String update = String.format(MapMarksUtil.UPDATE_NAME_CATEGORY_COLUMN,name,category,u);
		try {
			// 重複の検索
			Cursor c = db.query(MapMarksUtil.BASE_RECORD,
					new String[] { MapMarksUtil.PRIMARY_KEY_COLUMN },
					MapMarksUtil.URL_COLUMN + "=?", new String[] {u}, null,
					null, null);
			if(c.moveToFirst()){
				db.execSQL(update);
			}else{
				db.execSQL(insert);
			}
			
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
			Toast.makeText(this, "MapMarkの書き込みに失敗しました", Toast.LENGTH_LONG)
					.show();
		}
		helper.close();
		
		// debug用
		readUrl(u);
	}

	/**
	 * databaseからdataを取得する keyはURL
	 * Debug用
	 */
	private void readUrl(String key) {
		BookmarkDbHelper helper = new BookmarkDbHelper(this);
		SQLiteDatabase db = helper.getReadableDatabase();

		try {
			// 検索
			Cursor c = db.query(MapMarksUtil.BASE_RECORD,
					new String[] { "*" },
					MapMarksUtil.URL_COLUMN + "=?", new String[] {key}, null,
					null, null);
			if(c.moveToFirst()){
				StringBuilder sb = new StringBuilder();
				sb.append("key");
				sb.append(c.getInt(c.getColumnIndex(MapMarksUtil.PRIMARY_KEY_COLUMN)));
				sb.append(" url:");
				sb.append(c.getString(c.getColumnIndex(MapMarksUtil.URL_COLUMN)));
				sb.append(" name:");
				sb.append(c.getString(c.getColumnIndex(MapMarksUtil.NAME_COLUMN)));
				sb.append(" date:");
				sb.append(c.getString(c.getColumnIndex(MapMarksUtil.DATE_COLUMN)));

				Log.v(MapMarksUtil.LOGTAG,sb.toString());
			}
			
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
			Toast.makeText(this, "読み込みに失敗しました", Toast.LENGTH_LONG)
					.show();
		}
		helper.close();
	}
	
	/**
	 * databaseをdumpする
	 * for debug
	 */
	private void readDatabase(){
		BookmarkDbHelper helper = new BookmarkDbHelper(this);
		SQLiteDatabase db = helper.getReadableDatabase();
		
		try{
			Cursor c = db.query(MapMarksUtil.BASE_RECORD, null, null, null, null, null, null);
			
			if(c.moveToFirst()){
				do{
					StringBuilder sb = new StringBuilder();
					sb.append("key");
					sb.append(c.getInt(c.getColumnIndex(MapMarksUtil.PRIMARY_KEY_COLUMN)));
					sb.append(" url:");
					sb.append(c.getString(c.getColumnIndex(MapMarksUtil.URL_COLUMN)));
					sb.append(" name:");
					sb.append(c.getString(c.getColumnIndex(MapMarksUtil.NAME_COLUMN)));
					sb.append(" date:");
					sb.append(c.getString(c.getColumnIndex(MapMarksUtil.DATE_COLUMN)));

					Log.v(MapMarksUtil.LOGTAG,sb.toString());
				}while(c.moveToNext());
			}
				
			c.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		helper.close();
	}
}
