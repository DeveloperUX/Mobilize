package mobilize.me;

import android.app.Application;
import android.content.Intent;

/**
 * This is the starting point of our application
 * @author Moose
 *
 */
public class MobilizeApp extends Application {
	
	private static MobilizeApp app;

    public MobilizeApp() {
    	app = this;
    }

    public static MobilizeApp getApplication() {
        return app;
    }
    
	@Override
	public void onCreate() {
		super.onCreate();
		// Intention to start the main map activity
		Intent intent = new Intent(this, MapActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// Start the Map Activity
		startActivity(intent);

	}
	
	

}
