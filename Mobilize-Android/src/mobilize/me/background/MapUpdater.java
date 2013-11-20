package mobilize.me.background;

import android.os.Handler;

public class MapUpdater implements Runnable {
	
	Handler handler;
	
	public MapUpdater() {
		handler = new Handler();	
	}

	@Override
	public void run() {
		updatePins(); // update function
		handler.postDelayed(this, 5000); // Call run every 5 seconds	
	}

	private void updatePins() {
		// update the map pins
		
	}
	

}
