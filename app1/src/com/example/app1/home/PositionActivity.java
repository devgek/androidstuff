package com.example.app1.home;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.IntentSender;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app1.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.location.LocationClient;

public class PositionActivity extends Activity implements
GooglePlayServicesClient.OnConnectionFailedListener, ConnectionCallbacks{
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    private LocationClient locationClient;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.position_main);


		Button buttonWhereAmI = (Button) findViewById(R.id.butPositionWhereAmI);
		
		buttonWhereAmI.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				TextView tvLength = (TextView)findViewById(R.id.textPositionLength);
				TextView tvWidth = (TextView)findViewById(R.id.textPositionWidth);
				TextView tvTime = (TextView)findViewById(R.id.textPositionDateTime);
				TextView tvWhere = (TextView)findViewById(R.id.textPositionWhere);

//				LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//				Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

				Location location = locationClient.getLastLocation();
				
				if (location != null) {
					double lat = location.getLatitude();
					double longi = location.getLongitude();
					long time = location.getTime();
					
					tvLength.setText(Double.valueOf(longi).toString());
					tvWidth.setText(Double.valueOf(lat).toString());
					
					Date d = new Date(time);
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					tvTime.setText(df.format(d));
					
					Address address = getAddress(location);
					if (address != null) {
						tvWhere.setText(address.getPostalCode() + " " + address.getLocality() + " " +address.getAddressLine(0));
					}
				}
				else {
					tvLength.setText("no location data available");
				}
			}

		});

		locationClient = new LocationClient(this, this, this);
	}
	
	private Address getAddress(Location location) {
		if (location == null) return null;
		
		Geocoder geoCoder = new Geocoder(getApplicationContext(), Locale.getDefault());
		List<Address> addressList;
		try {
			addressList = geoCoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
		} catch (IOException e) {
			Log.e("PositionActivity", "Exception while getting geocode.");
			return null;
		}
		if (addressList != null && addressList.size() > 0) {
			return addressList.get(0);
		}
		
		return null;
	}
	
    /*
     * Called when the Activity becomes visible.
     */
    @Override
    protected void onStart() {
        super.onStart();
        // Connect the client.
        locationClient.connect();
    }
    /*
     * Called when the Activity is no longer visible.
     */
    @Override
    protected void onStop() {
        // Disconnecting the client invalidates it.
        locationClient.disconnect();
        super.onStop();
    }

	@Override
	public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(
                        this,
                        CONNECTION_FAILURE_RESOLUTION_REQUEST);
                /*
                 * Thrown if Google Play services canceled the original
                 * PendingIntent
                 */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
            /*
             * If no resolution is available, display a dialog to the
             * user with the error.
             */
        	Toast.makeText(this, "Connection failed with " + connectionResult.getErrorCode(), Toast.LENGTH_SHORT).show();
        }
	}

	@Override
	public void onConnected(Bundle arg0) {
		Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();	}

	@Override
	public void onDisconnected() {
        Toast.makeText(this, "Disconnected. Please re-connect.",
                Toast.LENGTH_SHORT).show();
	}

}
