package com.wireless.caravan;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

public class MapActivity extends Activity {
	
	private GoogleMap mMap;
	protected LocationManager locationManager;
	Location location;
	String provider;
	LocationListener locationListener;
	CameraPosition cameraPosition;
	Criteria criteria;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);		
		Intent intent = getIntent();
		Toast.makeText(getApplicationContext(),
                "Intent success", Toast.LENGTH_SHORT)
                .show();
		initilizeMap();
		map_navigation();
	}
	
	private void initilizeMap() {
        if (mMap == null) {
        	mMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();
 
            // check if map is created successfully or not
            if (mMap == null) {
                Toast.makeText(getApplicationContext(),
                        "unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }
        }
   }
	private void map_navigation()
	{
		 locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		 criteria = new Criteria();
		 provider = locationManager.getBestProvider(criteria, false);
		
		Boolean isGPSEnabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);
		
		if(isGPSEnabled==false)
			Toast.makeText(getApplicationContext(),  "GPS not enabled", Toast.LENGTH_SHORT).show(); 
		else
		{
			mMap.setMyLocationEnabled(true);
			mMap.getUiSettings().setMyLocationButtonEnabled(true);
			location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			
			if(location==null)
			{
				Toast.makeText(getApplicationContext(), "Location not known", Toast.LENGTH_SHORT).show(); 
			}
			else
			{
				Toast.makeText(getApplicationContext(),"Provider:"+provider, Toast.LENGTH_SHORT).show(); 

				
				mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).
						title("Car"));
				cameraPosition = new CameraPosition.Builder().target(
		                new LatLng(location.getLatitude(), location.getLongitude())).zoom(18).build();
				mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
				

				locationListener = new LocationListener() {

					@Override
					public void onLocationChanged(Location location) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(),location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show(); 
						mMap.clear();
						mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).
								title("Car"));
						cameraPosition = new CameraPosition.Builder().target(
				                new LatLng(location.getLatitude(), location.getLongitude())).zoom(18).build();
						mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
						
					}

					@Override
					public void onProviderDisabled(String provider) {
						// TODO Auto-generated method stub
						 Toast.makeText(getApplicationContext(), "Disabled provider " + provider,
							        Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onProviderEnabled(String provider) {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(), "Enabled new provider " + provider,
						        Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onStatusChanged(String provider, int status,
							Bundle extras) {
						// TODO Auto-generated method stub
						
					}
				};
				
				locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0, locationListener);
			}
			
		}
	}
	
	@Override
	  protected void onResume() {
	    super.onResume();
	    location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
	    mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).
				title("Car"));
		cameraPosition = new CameraPosition.Builder().target(
                new LatLng(location.getLatitude(), location.getLongitude())).zoom(18).build();
		mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
		
	    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0, locationListener);
	  }

	  // Remove the locationlistener updates when Activity is paused 
	  @Override
	  protected void onPause() {
	    super.onPause();
	    locationManager.removeUpdates(locationListener);
	  }

}