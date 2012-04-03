package co.jp.kobashin.mapmarks;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

public class MapMarksActivity extends Activity {
	/**
	 * TODO:<br>
	 *  ActionBarから設定画面へ
	 *  カテゴリ毎の表示を行う
	 *  　-> ExpandableなList表示
	 *  リスト右端のボタンで共有 -> Mapへ
	 *  長押しで編集Dialog表示
	 */

	/**
	 * 
	 * 
	 * 
	 * 
	 */
	
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final ActionBar mActionBar = getActionBar();
        
        
    
    
    }
    
	@Override
	protected void onResume() {
		super.onResume();

	
	
	
	}

    
    /**
     * Menu の Inflateをおこなう。
     *  getMenuInflaterからMenuInflaterを取得し、MenuInflater#inflate(Int res, Menu menu)
     *  を呼び出す
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.xml.menu, menu);
        return true;
    }
	
	
	
	
    
	@Override
	protected void onPause() {
		super.onPause();
	}

    
    
    
    
    
    
}