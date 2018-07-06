package com.example.road_bud;

import java.security.PublicKey;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SecondActivity extends Activity {
	
	Button route,about,track;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		
		route = (Button) findViewById(R.id.route_button);
		Typeface button_1 = Typeface.createFromAsset(getAssets(),
			      "fonts/y.n.w.u.a.y.ttf");
			      route.setTypeface(button_1);
		
		about =(Button) findViewById(R.id.aboutbutton);
		Typeface button_2 = Typeface.createFromAsset(getAssets(),
			      "fonts/y.n.w.u.a.y.ttf");
			      about.setTypeface(button_2);
			      
		track =(Button) findViewById(R.id.track_me);
		Typeface button_3 = Typeface.createFromAsset(getAssets(),
			      "fonts/y.n.w.u.a.y.ttf");
			      track.setTypeface(button_3);
		
		track.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(SecondActivity.this, MapActivity.class);
				startActivity(intent);
			}
		});
		
		about.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(SecondActivity.this, About.class);
				startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
		
		
		route.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent = new Intent(SecondActivity.this, RouteActivity.class);
				startActivity(intent);
				// TODO Auto-generated method stub
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
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
