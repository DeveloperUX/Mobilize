package mobilize.me;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class LocationService {
		
	private final LocationListener mGpsLocationListener;
    private final LocationListener mNetworkLocationListener;
    private LocationResultListener mLocationResultListener;
    private LocationManager mLocationManager;
    private Timer mTimer;
 
    public LocationService() {
        mGpsLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                mTimer.cancel();
                mLocationManager.removeUpdates(this);
                mLocationManager.removeUpdates(mNetworkLocationListener);
                mLocationResultListener.onLocationResultAvailable(location);
            }
 
            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
            }
 
            @Override
            public void onProviderEnabled(String s) {
            }
 
            @Override
            public void onProviderDisabled(String s) {
            }
        };
 
        mNetworkLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                mTimer.cancel();
                mLocationManager.removeUpdates(this);
                mLocationManager.removeUpdates(mGpsLocationListener);
                mLocationResultListener.onLocationResultAvailable(location);
 
            }
 
            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
            }
 
            @Override
            public void onProviderEnabled(String s) {
            }
 
            @Override
            public void onProviderDisabled(String s) {
            }
        };
    }
    
    private boolean mGpsEnabled;
    private boolean mNetworkEnabled;

	public boolean getLocation(Context context, LocationResultListener locationListener) {
		mLocationResultListener = locationListener;
        if (mLocationManager == null)
            mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        try {
            mGpsEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) { }

        try {
            mNetworkEnabled = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) { }

        if (!mGpsEnabled && !mNetworkEnabled)
            return false;

        if (mGpsEnabled)
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mGpsLocationListener);

        if (mNetworkEnabled)
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mNetworkLocationListener);

        mTimer = new Timer();
        mTimer.schedule(new LastLocationFetcher(), 20000);
        return true;
	}
	
	private class LastLocationFetcher extends TimerTask {
		 
        @Override
        public void run() {
 
            // remove GPS location listener
            mLocationManager.removeUpdates(mGpsLocationListener);
            // remove network location listener
            mLocationManager.removeUpdates(mNetworkLocationListener);
 
            Location gpsLoc = null, netLoc = null;
 
            // if we had GPS enabled, get the last known location from GPS provider
            if (mGpsEnabled)
                gpsLoc = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
 
            // if we had WiFi enabled, get the last known location from Network provider
            if (mNetworkEnabled)
                netLoc = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
 
 
            // if we had both providers, get the newest last location
            if (gpsLoc != null && netLoc != null) {
                if (gpsLoc.getTime() > netLoc.getTime())
                    // last location from the GPS provider is the newest
                    mLocationResultListener.onLocationResultAvailable(gpsLoc);
                else
                    // last location from the Network Provider is the newest
                    mLocationResultListener.onLocationResultAvailable(netLoc);
                return;
            }
 
            // looks like network provider is not available
            if (gpsLoc != null) {
                mLocationResultListener.onLocationResultAvailable(gpsLoc);
                return;
            }
 
            // looks like GPS provider is not available
            if (netLoc != null) {
                mLocationResultListener.onLocationResultAvailable(netLoc);
                return;
            }
 
            mLocationResultListener.onLocationResultAvailable(null);
        }
    }
}
