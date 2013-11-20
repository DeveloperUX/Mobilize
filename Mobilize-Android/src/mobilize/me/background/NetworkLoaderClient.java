package mobilize.me.background;

import java.util.List;

import mobilize.me.Protest;
import mobilize.me.ProtestMapSingleton;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;

public class NetworkLoaderClient implements LoaderCallbacks<List<Protest>> {

	private Context context;
	
	// To be used for support library: 
	// FragmentActivity context; 
	
	public NetworkLoaderClient(Context context) {
		this.context = context;
	}	

	@Override
	public Loader<List<Protest>> onCreateLoader(int id, Bundle args) {
		// TODO Auto-generated method stub
		return new NetworkLoader(context);
	}

	@Override
	public void onLoadFinished(Loader<List<Protest>> loader, List<Protest> data) {
		// Update the UI
		ProtestMapSingleton.getInstance().set(data);
	}

	@Override
	public void onLoaderReset(Loader<List<Protest>> loader) {
		// TODO Auto-generated method stub
		
	}



}
