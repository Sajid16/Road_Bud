package com.example.road_bud;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class About extends Activity {
	TextView st_info_1,st_info_2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		
		st_info_1 = (TextView)findViewById(R.id.rafitext);
		Typeface stinfo_1 = Typeface.createFromAsset(getAssets(),
			      "fonts/Mf Texas Spring.ttf");
			      st_info_1.setTypeface(stinfo_1);
			      
			      st_info_2 = (TextView)findViewById(R.id.shahitext);
					Typeface stinfo_2 = Typeface.createFromAsset(getAssets(),
						      "fonts/Mf Texas Spring.ttf");
						      st_info_2.setTypeface(stinfo_2);
						      
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.about, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
