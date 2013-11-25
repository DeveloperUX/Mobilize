package mobilize.me;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This is the starting point of our application
 * @author Moose
 *
 */
public class MobilizeApp extends Activity {
	
	private static MobilizeApp app;
	// The name of our Shared Preferences file
	public static final String PREFS_NAME = "MobilizePreferencesFile";
	// The app's Access Code
	public static final String ACCESS_CODE = "71413";

//    public MobilizeApp() {
//    	app = this;
//    }
//
//    public static MobilizeApp getApplication() {
//        return app;
//    }
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.login);
//		getLayoutInflater().inflate(login, root)
		
		boolean userSignedIn = isUserSignedIn();

		if(userSignedIn) {
			startMapActivity();
		}	
		
		Button signinBtn = (Button) findViewById(R.id.signin_btn);
		signinBtn.setOnClickListener( new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText signinAttempt = (EditText) findViewById(R.id.signin_user_code);
				String userEnteredText = signinAttempt.getText().toString();
				attemptUserSignin(userEnteredText);
			}
		});
	}
	
	protected void attemptUserSignin(String userEnteredText) {
		// TODO Auto-generated method stub
		if( userEnteredText.equals(ACCESS_CODE) ) {
			// Save the user's login credentials
			saveUserLogin();
			startMapActivity();
		}			
	}

	public boolean isUserSignedIn() {
		SharedPreferences settings = getSharedPreferences(MobilizeApp.PREFS_NAME, 0);
		// Get "hasLoggedIn" value. If the value doesn't exist yet false is returned
		boolean hasLoggedIn = settings.getBoolean("hasLoggedIn", false);
		return hasLoggedIn;
	}
	
	public void saveUserLogin() {
		// User has successfully logged in, save this information
		// We need an Editor object to make preference changes.
		SharedPreferences settings = getSharedPreferences(MobilizeApp.PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();

		//Set "hasLoggedIn" to true
		editor.putBoolean("hasLoggedIn", true);

		// Commit the edits!
		editor.commit();		
	}
	
	public void startMapActivity() {
	    // Go directly to the main activity
		// Intention to start the main map activity
		Intent intent = new Intent(this, OccupyMapActivity.class);
		// This should end this current activity
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		// Start the Map Activity
		startActivity(intent);
		// Remember to end this activity
		finish();		
	}
	
	

}
