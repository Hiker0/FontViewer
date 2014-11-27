package com.allen.fontviewer;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FontsViewer extends Activity implements OnItemClickListener {
	ListView listView = null;
	String[] list;
	AssetManager mAssetManager;
	TextView smallText,mediumText,largeText,numberlargeText;
	String num,word,upword;
	
	boolean isSystem = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.fonts_activity);
		
		listView = (ListView) this.findViewById(R.id.fonts_list);
		mAssetManager = this.getResources().getAssets();
		
		Intent intent = this.getIntent();
		isSystem = intent.getBooleanExtra("isSystem", false);
		
		if(isSystem){
			getSystemFonts();
		}else{
			getSelfFonts();
		}
		
		if(list != null){
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , list);
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(this);
		}
		smallText=(TextView) this.findViewById(R.id.small);
		mediumText=(TextView) this.findViewById(R.id.medium);
		largeText=(TextView) this.findViewById(R.id.large);
		numberlargeText=(TextView) this.findViewById(R.id.numberlarge);
		num = this.getString(R.string.font_num);
		word = this.getString(R.string.font_world);
		upword = this.getString(R.string.font_world_upcase);
	}

	void getSelfFonts(){
		try {
			list = mAssetManager.list("fonts");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void getSystemFonts(){
		
		File file = new File("/system/fonts");
		list = file.list();
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		this.setTitle(list[arg2]);
		String fontPath ;
		Typeface typeFace;
		
		if(isSystem){
			fontPath = "/system/fonts/"+list[arg2];
			typeFace = Typeface.createFromFile(fontPath);
		}else{
			fontPath = "fonts/"+list[arg2];
			typeFace = Typeface.createFromAsset(mAssetManager,fontPath);			
		}

		smallText.setTypeface(typeFace);
		smallText.setText(num+word+upword);
		
		mediumText.setTypeface(typeFace);
		mediumText.setText(num+word+upword);
		
		largeText.setTypeface(typeFace);
		largeText.setText(num+word+upword);
		
		numberlargeText.setTypeface(typeFace);
		numberlargeText.setText(num);
				
	}

	
	
}
