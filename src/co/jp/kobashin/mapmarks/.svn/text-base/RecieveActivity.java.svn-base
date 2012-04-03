package co.jp.kobashin.mapmarks;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import co.jp.kobashin.mapmarks.db.BookmarkDbHelper;
import co.jp.kobashin.mapmarks.fragment.EditDialogFragment;
import co.jp.kobashin.mapmarks.fragment.EditDialogFragment.OnEditDialogFragmentListener;
import co.jp.kobashin.mapmarks.util.MapMarksUtil;

public class RecieveActivity extends Activity implements OnEditDialogFragmentListener{
	private int mode;
	private String url = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// preferenceからModeの取得
		SharedPreferences pref = getSharedPreferences(MapMarksUtil.PREF_NAME, MODE_PRIVATE);
		mode = pref.getInt(MapMarksUtil.PREF_KEY_MODE, MapMarksUtil.INPUT_MODE);
		
		// intentの取得
		Intent intent = getIntent();
		if(intent != null){
			url = intent.getDataString();
			Log.v(MapMarksUtil.LOGTAG,url);
		
			// urlが有効ならDBに登録
			if(url != null){
				// Modeによって処理を変動する
				// 簡易Mode     -> 保存しながらMapに直接移動する
				// きっちりMode  -> カテゴリ、名前を設定させる
				if(mode == MapMarksUtil.TEMP_MODE){
					saveUrl(url);
					finish();
				}else{
					// Dialogを表示する
					EditDialogFragment edit = new EditDialogFragment(this, url);
					edit.show(getFragmentManager(), "edit_fragment");
				}
			}
		}
	}
	
	
	/**
	 * urlのみで保存をかける。
	 * category,nameにはtemp-dataを入力する
	 * @param u
	 */
	private void saveUrl(String u){
		saveUrl(u, MapMarksUtil.TEMP_CATEGOTY, null);
	}
	
	/**
	 * databaseにinsertをかける(to InputDataService)
	 * @param u
	 * @param category
	 * @param name
	 */
	private void saveUrl(String u, String category, String name){
		if(name == null){
			name = "NULL";
		}
		
		String[] val = new String[3];
		val[0] = u;
		val[1] = category;
		val[2] = name;
		
		// InputDataServiceに書き込み処理をさせる
		Intent intent = new Intent(this, InputDataService.class);
		intent.putExtra(MapMarksUtil.CMD_ID, MapMarksUtil.CMD_SAVE);
		intent.putExtra(MapMarksUtil.CMD_VAL, val);
		this.startService(intent);
	}

	@Override
	public void onEditDialogNegativeClick(Fragment fragment) {
		((EditDialogFragment)fragment).dismiss();
		finish();
	}

	@Override
	public void onEditDialogPositiveClick(Fragment fragment, View name, View category) {
		// DBへの保存はService経由で行う
		saveUrl(url,((EditText)category).getText().toString(),((EditText)name).getText().toString());
		
		((EditDialogFragment)fragment).dismiss();
		url = null;
		finish();
	}

	
	
	
	
}
