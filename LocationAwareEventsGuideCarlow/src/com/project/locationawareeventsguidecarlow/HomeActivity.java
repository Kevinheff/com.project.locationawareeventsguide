package com.project.locationawareeventsguidecarlow;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Dialog;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;


public class HomeActivity extends FragmentActivity implements LocationListener {

	private GoogleMap googleMap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (isGooglePlay()) {
			setContentView(R.layout.home);
			setupMap();
		}
		googleMap.addMarker(new MarkerOptions().
				position(new LatLng(52.827519,-6.936064)).title("IT Carlow"));
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.home, menu);
		
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		if (item.getItemId() == R.id.legalNoticesMI) {
			startActivity(new Intent(this, LegalNoticesActivity.class));
		}
		return super.onOptionsItemSelected(item);
	}




	// Checks if Google Play is available 
	public boolean isGooglePlay() {
		
		int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		boolean returnValue;
		
		if (status == ConnectionResult.SUCCESS) {
			returnValue = true;
		}
		else {
			((Dialog) GooglePlayServicesUtil.getErrorDialog(status, this, 10)).show();
			returnValue = false;
		}
		return returnValue;
	}
	
	
	private void setupMap() {
		
		if (googleMap == null) {
			googleMap = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
			
				if (googleMap != null) {
				// initialise map code
				googleMap.setMyLocationEnabled(true);
				LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);
				String provider = lm.getBestProvider(new Criteria(), true);
				
				if (provider ==  null) {
					onProviderDisabled(provider);
				}
				
			Location loc = lm.getLastKnownLocation(provider);
				if (loc != null) {
					onLocationChanged(loc);
				}
			}
		}
	}

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
		LatLng latlng = new LatLng(location.getLatitude(), location.getLongitude());
		googleMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
		googleMap.animateCamera(CameraUpdateFactory.zoomTo(10));
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
}
