package mobilize.me.background;

import java.util.ArrayList;
import java.util.List;

import mobilize.me.Protest;
import mobilize.me.ServerAccess;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

public class NetworkLoader extends AsyncTaskLoader<List<Protest>> {

	/*********************************************************************/
	/** (4) Observer which receives notifications when the data changes **/
	/*********************************************************************/

	// NOTE: Implementing an observer is outside the scope of this post (this example
	// uses a made-up "SampleObserver" to illustrate when/where the observer should 
	// be initialized). 

	// The observer could be anything so long as it is able to detect content changes
	// and report them to the loader with a call to onContentChanged(). For example,
	// if you were writing a Loader which loads a list of all installed applications
	// on the device, the observer could be a BroadcastReceiver that listens for the
	// ACTION_PACKAGE_ADDED intent, and calls onContentChanged() on the particular 
	// Loader whenever the receiver detects that a new application has been installed.
	// Please don�t hesitate to leave a comment if you still find this confusing! :)
	private NetworkLoaderClient mObserver;

	// We hold a reference to the Loader�s data here.
	private List<Protest> mData;

	public NetworkLoader(Context ctx) {
		// Loaders may be used across multiple Activitys (assuming they aren't
		// bound to the LoaderManager), so NEVER hold a reference to the context
		// directly. Doing so will cause you to leak an entire Activity's context.
		// The superclass constructor will store a reference to the Application
		// Context instead, and can be retrieved with a call to getContext().
		super(ctx);
	}

	/****************************************************/
	/** (1) A task that performs the asynchronous load **/
	/****************************************************/

	@Override
	public List<Protest> loadInBackground() {
		// This method is called on a background thread and should generate a
		// new set of data to be delivered back to the client.
		List<Protest> protests = new ArrayList<Protest>();

		// TODO: Perform the query here and add the results to 'data'.
		// Get the protest data from the backend
		protests = ServerAccess.updateFromServer("");

		return protests;
	}

	/********************************************************/
	/** (2) Deliver the results to the registered listener **/
	/********************************************************/

	@Override
	public void deliverResult(List<Protest> data) {
		if (isReset()) {
			// The Loader has been reset; ignore the result and invalidate the data.
			releaseResources(data);
			return;
		}

		// Hold a reference to the old data so it doesn't get garbage collected.
		// We must protect it until the new data has been delivered.
		List<Protest> oldData = mData;
		mData = data;

		if (isStarted()) {
			// If the Loader is in a started state, deliver the results to the
			// client. The superclass method does this for us.
			super.deliverResult(data);
		}

		// Invalidate the old data as we don't need it any more.
		if (oldData != null && oldData != data) {
			releaseResources(oldData);
		}
	}

	/*********************************************************/
	/** (3) Implement the Loader�s state-dependent behavior **/
	/*********************************************************/

	@Override
	protected void onStartLoading() {
		if (mData != null) {
			// Deliver any previously loaded data immediately.
			deliverResult(mData);
		}

		// Begin monitoring the underlying data source.
		if (mObserver == null) {
			mObserver = new NetworkLoaderClient(getContext());
			// TODO: register the observer
		}

		if (takeContentChanged() || mData == null) {
			// When the observer detects a change, it should call onContentChanged()
			// on the Loader, which will cause the next call to takeContentChanged()
			// to return true. If this is ever the case (or if the current data is
			// null), we force a new load.
			forceLoad();
		}
	}

	@Override
	protected void onStopLoading() {
		// The Loader is in a stopped state, so we should attempt to cancel the 
		// current load (if there is one).
		cancelLoad();

		// Note that we leave the observer as is. Loaders in a stopped state
		// should still monitor the data source for changes so that the Loader
		// will know to force a new load if it is ever started again.
	}

	@Override
	protected void onReset() {
		// Ensure the loader has been stopped.
		onStopLoading();

		// At this point we can release the resources associated with 'mData'.
		if (mData != null) {
			releaseResources(mData);
			mData = null;
		}

		// The Loader is being reset, so we should stop monitoring for changes.
		if (mObserver != null) {
			// TODO: unregister the observer
			mObserver = null;
		}
	}

	@Override
	public void onCanceled(List<Protest> data) {
		// Attempt to cancel the current asynchronous load.
		super.onCanceled(data);

		// The load has been canceled, so we should release the resources
		// associated with 'data'.
		releaseResources(data);
	}

	private void releaseResources(List<Protest> data) {
		// For a simple List, there is nothing to do. For something like a Cursor, we 
		// would close it in this method. All resources associated with the Loader
		// should be released here.
	}

}