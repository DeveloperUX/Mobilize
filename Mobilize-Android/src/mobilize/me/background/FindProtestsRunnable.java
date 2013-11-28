//package mobilize.me.background;
//
//import mobilize.me.ProtestMapSingleton;
//import mobilize.me.ServerAccess;
//import android.os.AsyncTask;
//import android.os.Handler;
//import android.view.View;
//
//
//public class FindProtestsRunnable implements Runnable {
//	
//	private PointsRetrievalTask protestRetrievalTask;
//	private Handler recurringTaskHandler;
//
//	public FindProtestsRunnable( Handler recurHandler ) {
//    	protestRetrievalTask = new PointsRetrievalTask();
//		recurringTaskHandler = recurHandler;
//	}
//
//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//    	protestRetrievalTask.execute();
//    	// rerun this task every 60 seconds 
//		recurringTaskHandler.postDelayed(recurringTask, 60000);		
//	}
//	
//	private class PointsRetrievalTask extends AsyncTask<String, Integer, Void> {
//		
//		@Override
//		protected void onPreExecute(){
//			progressbar.setVisibility(View.VISIBLE);	// show the progress bar
//			setProgressBarIndeterminate(true);
//			setProgressBarIndeterminateVisibility(true);	
//		}
//		
//		@Override
//		protected Void doInBackground(String... params) {
//			// Get the protest data from the backend
//			ProtestMapSingleton.getInstance().set( ServerAccess.updateFromServer("") );
//			return null;
//		}
//		
//		@Override
//		protected void onPostExecute(Void result){
//			// update the map with the new protest spots
//			updateProtestPoints();
//			progressbar.setVisibility(View.INVISIBLE);	// hide the progressbar
//		}
//	}
//	
//	
//
//}
