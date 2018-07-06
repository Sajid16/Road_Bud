package com.example.road_bud;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class FirstActivity extends Activity {
	TextView tx,mx;
	//Button tab;
	private final int SPLASH_DISPLAY_LENGTH = 2000;
	//ViewPager slid;
	//CustomEventAdapter photo;
	//PhotoPageAdaptor photoadapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		
		TextView tx = (TextView)findViewById(R.id.road);
	      Typeface custom_font = Typeface.createFromAsset(getAssets(),
	      "fonts/BlackedOut-Regular.ttf");
	      tx.setTypeface(custom_font);
	      
	       
			tx.setTextColor(Color.parseColor("#CCFFCC"));
		
		new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(FirstActivity.this,SecondActivity.class);
                FirstActivity.this.startActivity(mainIntent);
                FirstActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
			
	}
		
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.first, menu);
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
