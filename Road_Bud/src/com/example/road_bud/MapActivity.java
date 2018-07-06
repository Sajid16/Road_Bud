package com.example.road_bud;

import java.io.IOException;
import java.util.List;

import com.example.road_bud.GPSTracker;
import com.example.road_bud.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;

import android.app.Dialog;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MapActivity extends FragmentActivity {
	
	Button go;
	Marker markerGps;
	GPSTracker gps;
	double latitudeGps,longitudeGps;
	String locality  , country , localityGps , subLocalityGps;
	Polyline line;
	
	private static final int GPS_ERRORDIALOGE_REQUEST = 9001;
	GoogleMap mMap;
	
	private static final double DHAKA_LAT = 23.814013,
			DHAKA_LAN = 90.408854;
	
	private static float defultzoom = 16;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		
		if (servicesOK()) {
			setContentView(R.layout.draw_map);
			
			if(initMap()){
				mMap.getUiSettings().setZoomControlsEnabled(true);
				gps = new GPSTracker(MapActivity.this);
				gps.getLocation();
				if(gps.canGetLocation()){                    
	                latitudeGps = gps.getLatitude();
	                longitudeGps = gps.getLongitude(); 
	                /*
	                Geocoder geocoder = new Geocoder(this);
					
					List<Address> addresses;
					try {
						addresses = geocoder.getFromLocation(latitudeGps,longitudeGps, 1);
						localityGps = addresses.get(0).getLocality();
						subLocalityGps = addresses.get(0).getSubLocality();
					} catch (IOException e) {
						e.printStackTrace();
					}		*/						
					//MapStateManager mgr = new MapStateManager(this);
					//mgr.setSubLocalityGPS(subLocalityGps);
					//mgr.setLocalityGps(localityGps);
	                
	                gotoLocation(latitudeGps, longitudeGps,defultzoom);
	                //gotoLocation(DHAKA_LAT, DHAKA_LAN, defultzoom);
	                
	                SetMarkerGps(latitudeGps, longitudeGps);  
				}else{
	                gps.showSettingsAlert();
	            }
				/*Toast.makeText(this, "Ready to Map", Toast.LENGTH_SHORT).show();
				gotoLocation(DHAKA_LAT, DHAKA_LAN, defultzoom);
				SetMarkerGps(DHAKA_LAT ,DHAKA_LAN);*/
			
			}
			else{
				Toast.makeText(this, "Map not avilable", Toast.LENGTH_SHORT).show();
			}
			
			}	
		
		else{
			Toast.makeText(this, "Cannot Find map", Toast.LENGTH_SHORT).show();
		}
		
		go = (Button) findViewById(R.id.go);
		
		go.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				geoLocate(v);
			}
		});
	}
	
	//set the marker point on location
	private void SetMarkerGps(double latitude , double longitude){
		  if(markerGps != null){
		         markerGps.remove();
		        }
		        
		        MarkerOptions options = new MarkerOptions()
		   .position(new LatLng(latitude, longitude))
		   .anchor(.5f, .5f)
		         .icon(BitmapDescriptorFactory.fromResource(R.drawable.mark));
		        markerGps = mMap.addMarker(options);        
		 }
	
	//set location with moving camera
	private void gotoLocation(double lat, double lng, float zoom) {
		// TODO Auto-generated method stub
		LatLng ll = new LatLng(lat, lng);
		
		//set the camera on the location
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
		mMap.moveCamera(update);
	}

	
	//check for net and check if Google Play Service is added on the phone
	public boolean servicesOK(){
		int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		
		//check google play service is available or not
		if (isAvailable == ConnectionResult.SUCCESS){
			return true;
		}
		
		else if(GooglePlayServicesUtil.isUserRecoverableError(isAvailable)){
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvailable, this, GPS_ERRORDIALOGE_REQUEST);
			dialog.show();
		}
		else{
			Toast.makeText(this, "Google Map not avialable", Toast.LENGTH_SHORT).show();
		}
		return false;
	}
	
	
	//show the google map
	private boolean initMap(){
		if(mMap == null){
			SupportMapFragment mapFrag = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
			mMap = mapFrag.getMap();
		}
		return (mMap != null);
	}
 
	//set the location when place is searched 
	public void geoLocate(View v){	
		try{
			hideSoftKeyboard(v);
			
			//input the place name
			EditText et = (EditText) findViewById(R.id.edtloc);
			String loc = et.getText()+"";
			
			//track the place from google address list
			Geocoder gc = new Geocoder(this);
			List<Address> list = gc.getFromLocationName(loc, 1);
			Address add = list.get(0);
			
			//get the locality name and show in toast
			String locality = add.getLocality();
			Toast.makeText(this, locality, Toast.LENGTH_SHORT).show();
			
			//find the searched place lat and lng
			double lat = add.getLatitude();
			double lng = add.getLongitude();
			
			//set the map on the place
			gotoLocation(lat, lng, defultzoom);
			
			//set the marker
			SetMarkerGps(lat ,lng);
			
		}catch(Exception e){
			Toast.makeText(this, "Location not found", Toast.LENGTH_SHORT).show();
		}
	}

	
	//show the key board
	private void hideSoftKeyboard(View v) {
		// TODO Auto-generated method stub
		InputMethodManager inputManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); 

		inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
	}
	
}
