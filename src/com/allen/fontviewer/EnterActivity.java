package com.allen.fontviewer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EnterActivity extends Activity implements OnClickListener {

	Button button1,button2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.enter_activity);
		button1 = (Button) this.findViewById(R.id.button1);
		button2 = (Button) this.findViewById(R.id.button2);
		
		button1.setText("demon fonts");
		button2.setText("system fonts");
		
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		
	}
	
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id = v.getId();
		Intent intent = new Intent();
		intent.setClass(this, FontsViewer.class);
		if(id == R.id.button1){
			intent.putExtra("isSystem", false);
		}else if(id == R.id.button2){
			intent.putExtra("isSystem", true);
		}
		
		this.startActivity(intent);
	}
	
	
}
