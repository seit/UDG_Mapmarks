package co.jp.kobashin.mapmarks.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import co.jp.kobashin.mapmarks.R;

public class EditDialogFragment extends DialogFragment implements OnClickListener {
	private String url = null;
	private Context mContext;
	private OnEditDialogFragmentListener listener;
	private View rootView = null;
	private View name_edit;
	private View category_edit;
	private AlertDialog.Builder builder = null;
	
	public EditDialogFragment(Context c, String u){
		url = u;
		mContext = c;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setStyle(DialogFragment.STYLE_NORMAL,
				android.R.style.Theme_Holo_Light_Dialog);
	}

	@Override
	public void onResume() {
		super.onResume();
		getDialog().setCancelable(false);
		getDialog().setTitle("MapMarks");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.dialog_layout, container, false);
		name_edit = v.findViewById(R.id.name_edit);
		category_edit = v.findViewById(R.id.category_edit);
		v.findViewById(R.id.ok_button).setOnClickListener(this);
		v.findViewById(R.id.ng_button).setOnClickListener(this);
		v.findViewById(R.id.category_button).setOnClickListener(this);
		return v;
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
			listener = (OnEditDialogFragmentListener)activity;
	}

	
	public interface OnEditDialogFragmentListener{
		void onEditDialogNegativeClick(Fragment fragment);
		void onEditDialogPositiveClick(Fragment fragment,View name, View category);
	}


	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.category_button:
		case R.id.ng_button:
			listener.onEditDialogNegativeClick(this);
		case R.id.ok_button:
			listener.onEditDialogPositiveClick(this,name_edit, category_edit);
		}
	}
	

}
