package com.example.road_bud;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.renderscript.Mesh.Primitive;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RouteActivity extends Activity {

	EditText frmtxt, totxt;
	TextView ruttxt;
	Button go;
	TextView frnt_txt,bus_rut;
	String ftxt,ttxt,bus,route;
	int flage = 1,i,found;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_route);
		
		bus_rut = (TextView)findViewById(R.id.busroute);
		Typeface busroot = Typeface.createFromAsset(getAssets(),
			      "fonts/Mf Texas Spring.ttf");
			      bus_rut.setTypeface(busroot);
			     
			      
			      
		frnt_txt =(TextView) findViewById(R.id.showtext);
		Typeface frnttxt = Typeface.createFromAsset(getAssets(),
			      "fonts/Mf Texas Spring.ttf");
			      frnt_txt.setTypeface(frnttxt);

		frmtxt = (EditText) findViewById(R.id.from);
		Typeface frm_txt = Typeface.createFromAsset(getAssets(),
			      "fonts/Mf Texas Spring.ttf");
			      frmtxt.setTypeface(frm_txt);
		frmtxt.setTextColor(Color.parseColor("#00B8FF"));

		totxt = (EditText) findViewById(R.id.to);
		Typeface to_txt = Typeface.createFromAsset(getAssets(),
			      "fonts/Mf Texas Spring.ttf");
			      totxt.setTypeface(to_txt);
		totxt.setTextColor(Color.parseColor("#00B8FF"));

		
			      
		ruttxt = (TextView) findViewById(R.id.routes);
		Typeface rut_txt = Typeface.createFromAsset(getAssets(),
			      "fonts/Mf Texas Spring.ttf");
			      ruttxt.setTypeface(rut_txt);

		go = (Button) findViewById(R.id.gobutton);
		Typeface go_button = Typeface.createFromAsset(getAssets(),
			      "fonts/Mf Texas Spring.ttf");
			      go.setTypeface(go_button);

		//inserting the bus and route data
		go.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ShowData();
			}
		});
	}

	//inserting data in the Road database
	public void CallData(){
		flage = 0;
		
		//create database
		SQLiteDatabase db = openOrCreateDatabase("Road", MODE_PRIVATE, null);
		
		//removing all the dummy data previously inserted
		db.execSQL("DROP TABLE IF EXISTS BUSROUTE");
		
		//create table with column
		db.execSQL("CREATE TABLE IF NOT EXISTS BUSROUTE (FROMGO VARCHAR,TOGO VARCHAR,BUS VARCHAR,ROUTE VARCHAR);");

		
		//inserting data into the table
		db.execSQL("INSERT INTO BUSROUTE VALUES ('UTTARA, MOHAKHALI, NABISCO','UTTARA, MOHAKHALI, NABISCO','BOLAKA, GAJIPUR PORIBAHAN, PROVATI BONOSRI','ABDULLAHPUR, UTTARA, MOHAKHALI, NABISCO')");
		db.execSQL("INSERT INTO BUSROUTE VALUES ('MIRPUR ','NABISCO','VIP27, PROJAPOTI, PROVATI ','ABDULLAHPUR, UTTARA, MOHAKHALI, NABISCO')");
		//db.execSQL("INSERT INTO BUSROUTE VALUES ('MIRPUR ','NABISCO','x,y,z ','a,b,c')");
		db.execSQL("INSERT INTO BUSROUTE VALUES ('MIRPUR-1, MIRPUR-2, MIRPUR-10, KALSHI','MIRPUR-1, MIRPUR-2, MIRPUR-10, KALSHI','KONOK','MIRPUR-1, MIRPUR-2, MIRPUR-10, KALSHI')");
		db.execSQL("INSERT INTO BUSROUTE VALUES ('ABDULLAHPUR, FARMGATE, UTTARA, AIRPORT, AZIMPUR, KHILKHET, MOHAKHALI, BONANI, MOTIJHEEL','ABDULLAHPURUTTARA, AIRPORT, AZIMPUR, KHILKHET, MOHAKHALI, BONANI, FARMGATE, MOTIJHEEL','BRTC','ABDULLAHPUR, UTTARA, AIRPORT, AZIMPUR, KHILKHET, MOHAKHALI, BONANI, FARMGATE, MOTIJHEEL')");
		db.execSQL("INSERT INTO BUSROUTE VALUES ('NEW MARKET, FARMGATE, GAZIPUR CHOURASTA, AZIMPUR, UTTARA, AIRPORT, KHILKHET, MOHAKHALI, BONANI,','NEW MARKET, UTTARA, AIRPORT, KHILKHET, MOHAKHALI, BONANI, FARMGATE, GAZIPUR CHOURASTA, AZIMPUR','VIP','NEW-MARKET, UTTARA, AIRPORT, KHILKHET, MOHAKHALI, BONANI, FARMGATE, GAZIPUR CHOURASTA, AZIMPUR')");
		db.execSQL("INSERT INTO BUSROUTE VALUES ('MIRPUR, ANSARCAMP, UTTARA, AIRPORT, BONANI, KHILKHET, ABDULLAHPUR','MIRPUR, AIRPORT, BONANI, KHILKHET, ANSARCAMP, UTTARA, ABDULLAHPUR','PROJAPOTI PORIBAHON','MIRPUR, UTTARA, AIRPORT, BONANI, KHILKHET, ANSARCAMP, ABDULLAHPUR')");
		db.execSQL("INSERT INTO BUSROUTE VALUES ('JATRABARI, MALIBAG, NABISCO, MOHAKHALI, RAMPURA, UTTARA, AIRPORT, BONANI, KHILKHET, NOTUNBAZAR','JATRABARI, MALIBAG, NABISCO, MOHAKHALI, RAMPURA, NOTUNBAZAR, UTTARA, AIRPORT, BONANI, KHILKHET','JATRABARI PORIBAHAN','JATRABARI, MALIBAG, NABISCO, MOHAKHALI, RAMPURA, NOTUNBAZAR, UTTARA, AIRPORT, BONANI, KHILKHET')");
		
		db.execSQL("INSERT INTO BUSROUTE VALUES ('GULISTAN, POLTON, MOGBAZAR, MOHAKHALI','GULISTAN, POLTON, MOGBAZAR, MOHAKHALI','PROVATI BANASHREE','GULISTAN, POLTON, MOGBAZAR, MOHAKHALI')");
		db.execSQL("INSERT INTO BUSROUTE VALUES ('MOHAKHALI, AIRPORT, UTTARA, ABDULLAHPUR','MOHAKHALI, AIRPORT, UTTARA, ABDULLAHPUR','ASHULIA CLASSIC','MOHAKHALI, AIRPORT, UTTARA, ABDULLAHPUR')");
		db.execSQL("INSERT INTO BUSROUTE VALUES ('GABTOLI, ABDULLAHPUR, MIRPUR, GAZIPUR CHOURASTA, KALSHI','GABTOLI, ABDULLAHPUR, MIRPUR, GAZIPUR CHOURASTA, KALSHI','BOSUMOTI','GABTOLI, ABDULLAHPUR, MIRPUR, GAZIPUR CHOURASTA, KALSHI')");
		db.execSQL("INSERT INTO BUSROUTE VALUES ('MIRPUR, ANSARCAMP, UTTARA, ABDULLAHPUR, KALSHI','MIRPUR, ANSARCAMP, UTTARA, ABDULLAHPUR, KALSHI','NEW PALLABI EXPRESS','MIRPUR, ANSARCAMP, UTTARA, ABDULLAHPUR, KALSHI')");
		db.execSQL("INSERT INTO BUSROUTE VALUES ('SAVAR, GABTOLI, SHYAMOLI, AGARGAO, KAKOLI','SAVAR, GABTOLI, SHYAMOLI, AGARGAO, KAKOLI','RUPKATHA','SAVAR, GABTOLI, SHYAMOLI, AGARGAO, KAKOLI')");
		db.execSQL("INSERT INTO BUSROUTE VALUES ('GULISTAN, MOGBAZAR, MOHAKHALI, TONGI, GAZIPUR','GULISTAN, MOGBAZAR, MOHAKHALI, TONGI, GAZIPUR','GAZIPUR PARIBAHAN','GULISTAN, MOGBAZAR, MOHAKHALI, TONGI, GAZIPUR')");
		db.execSQL("INSERT INTO BUSROUTE VALUES ('TONGI, AIRPORT, RAMPURA, MALIBAG, JATRABARI','TONGI, AIRPORT, RAMPURA, MALIBAG, JATRABARI','JM SITTING','TONGI, AIRPORT, RAMPURA, MALIBAG, JATRABARI')");
		db.execSQL("INSERT INTO BUSROUTE VALUES ('JATRABARI, KHILGAO, MALIBAG, RAMPURA, SAIDABAD, BISSOROAD, AIRPORT, TONGI, NOTUNBAZAR, BADDA','JATRABARI, KHILGAO, MALIBAG, RAMPURA, SAIDABAD, BISSOROAD, AIRPORT, TONGI, NOTUNBAZAR, BADDA','GREAT TURAG','JATRABARI, KHILGAO, MALIBAG, RAMPURA, SAIDABAD, BISSOROAD, AIRPORT, TONGI, NOTUNBAZAR, BADDA')");
		db.execSQL("INSERT INTO BUSROUTE VALUES ('SADARGHAT, GULISTAN, BISSOROAD, AIRPORT, KURIL, RAMPURA, GAZIPUR, TONGI','SADARGHAT, GULISTAN, BISSOROAD, AIRPORT,KURIL,RAMPURA,GAZIPUR,TONGI','SUPROVAT','SADARGHAT, GULISTAN, BISSOROAD, AIRPORT, KURIL, RAMPURA, GAZIPUR, TONGI')");
		db.execSQL("INSERT INTO BUSROUTE VALUES ('AIRPORT, UTTARA, ABDULLAHPUR, GULISTAN, SHAHBAG, FARMGATE','AIRPORT, UTTARA, ABDULLAHPUR, GULISTAN, SHAHBAG, FARMGATE','AIRPORT BONGONODHU AVENUE','AIRPORT, UTTARA, ABDULLAHPUR, GULISTAN, SHAHBAG, FARMGATE')");
		db.execSQL("INSERT INTO BUSROUTE VALUES ('SAYEDABAD, UTTARA, MALIBAG, RAMPURA, BADDA, AIRPORT, TONGI','SAYEDABAD, UTTARA, MALIBAG, RAMPURA, BADDA, AIRPORT, TONGI','ANABIL SUPER','SAYEDABAD, MALIBAG, RAMPURA, BADDA, AIRPORT, TONGI')");
		
		db.close();
	}
	
	//showing the data from database
	public void ShowData(){
		try{
				found = 0;
				if(flage == 1)  // database will call only once while running
					CallData();

				//clear the Text View for next output
				ruttxt.setText("");
				
				ftxt = frmtxt.getText()+"";
				ttxt = totxt.getText()+"";
				
				//checking if the inputs are not null
				if(ftxt.length()!=0 && ttxt.length()!=0){ 

					ftxt = "%"+ftxt+"%";  //making for like operator
					ttxt = "%"+ttxt+"%";
					
					  //clear the Text View for next output
					ruttxt.setText("");

					//open the data base
					SQLiteDatabase db2 = openOrCreateDatabase("Road", MODE_PRIVATE, null);
					
					//searching from the table using query by the Cursor class obj
					final Cursor cursor = db2.rawQuery("SELECT * FROM BUSROUTE WHERE FROMGO LIKE '"+ftxt+"' AND TOGO LIKE '"+ttxt+"'", null);

					//checking for the data from table in 1st row
					if(cursor.moveToFirst())
					{
						found = 1;
						i=1;
						do{
							//fetching out data from BUS and ROUTE column 
							bus = cursor.getString(cursor.getColumnIndex("BUS"));
							route = cursor.getString(cursor.getColumnIndex("ROUTE"));
							
							//print all the same type data in append() 
							ruttxt.append("Bus "+i+": "+bus+"\n"+"Route: "+route+"\n\n");
													
							//color the Text View
							ruttxt.setTextColor(Color.parseColor("#ECF0F1"));
							i++;

						}while(cursor.moveToNext()!=false);
					}
					db2.close();
					
					//check for the null data
					if(found == 0){
						Toast.makeText(getApplicationContext(), "Route not found", Toast.LENGTH_SHORT).show();
					}
				}
				else{
					Toast.makeText(getApplicationContext(), "Please Enter Your Destination", Toast.LENGTH_SHORT).show();
				}
			
		}catch(Exception e){
			Toast.makeText(getApplicationContext(), "I am crashed", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.route, menu);
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
