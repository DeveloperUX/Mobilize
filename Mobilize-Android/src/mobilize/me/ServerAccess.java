package mobilize.me;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ServerAccess extends Activity {
	
	public static final boolean DEV = false;

	private static String testImageStr = "/9j/4AAQSkZJRgABAQEAYABgAAD/4QAWRXhpZgAASUkqAAgAAAAAAAAAAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAAiAC0DASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwDx2yv5J3kN7BbTwRIXkaSEbsdANwwckkD2zntTzJpFyd01hd2jnq9vOGU++JOf/Hq3YNEhEJjWPajEMQrEZI6c/iatQeGoCwAjMQPPmfMT+Y5pvQFdnOR6NZyfNa6vFjHS6jaH/wAe5X9a6nxP4m8SeI9EsNOvLa0ltLJR5cliiybiBjJKk449gPbIzUf/AAhsU37wWk7jP3iX5qk3hWO1nKvbXCtjKkFgT+PqKzdu5aRzCp99JSYz3ST5Sfp7iqTRHcQCfqB1ruJtOBtzBLHJKvX96zOR+fSqn9mWn8VvM/1lbj9aFIHE6+0l0pSm+6demcD/ABNdDYapodvICur3UWAMMsfeuMW80LjbY6u4/wCuOKl+06V1XSNXPv5f/wBatHhU+pCrtdD0/wD4SvSdm1fFupdsfuj0rm/EOp6PcafLLb63cT3kfzxCaDgnnIY56YJB+uewrlGvNNQZbR9TUf7WB/Sqs+qaRHGWk0y8C4/jcYP6VP1SPcPbvsa5ls3jHm36eZgFwFLDOOcHvzx+FUZBYluLse/yGseHWtLS3iVrGTKxqpImHJA6/dqKTWdMLfLZS4/66f8A1qtUI9xOs+xy8csm7HmP+dEjNu6nr60UUEEsQBxkA81digi/55J/3yPSiimMWSCHn91H/wB8iqzRRg/6tP8AvkUUUgR//9k=";
	
	private static String TAG = ServerAccess.class.getName();
	public static String DEV_SITE = "http://192.168.1.2";
	public static String PORT = "5000";
	public static String PROD_SITE = "http://mobilize-me.herokuapp.com";
	
//	public static String URL = DEV_SITE + ":" + PORT;
	public static String URL = PROD_SITE;

    /**********  File Path *************/
    final String uploadFilePath = "/mnt/sdcard/";
    final String uploadFileName = "service_lifecycle.png";

	public static String sendToServerX(String imageBase64) {
		
		String URL = DEV_SITE + ":" + PORT;
		
		HttpClient httpClient = new DefaultHttpClient();
		

		//will hold the final result
	    String result = "";	    
	    // Attempt to contact server
	    try{ 
	    	// Set the URL to the specific script we want to run
	        HttpPost httppost = new HttpPost( URL + "/post" );
	        // Encode the List and insert it into the Post object
//            httppost.setEntity( new UrlEncodedFormEntity(imageBase64) );
            // prepare the handler that will handle the response
            ResponseHandler<String> handler = new BasicResponseHandler(); 
            // Execute post and get the result
            result = httpClient.execute( httppost, handler );
            
	    } catch(Exception e) {
	    	Log.e( TAG, "Error in http connection "+e.toString());
	    }
	    //FEEDBACK FOR DEBUGGING
	    Log.e( TAG, "RESULT = " + result );
	    // return the final JSON result
	    return result;
	}
	
	public static String sendToServerAsync(String encodedImageStr) {
		
		String URL = DEV_SITE + ":" + PORT;
		
		RequestParams params = new RequestParams();
	    params.put("filename","MyPictureName");
	    params.put("image", encodedImageStr);
	    AsyncHttpClient client = new AsyncHttpClient();
	    client.addHeader("Content-Type", "application/json");
	    
	    client.post( URL + "/post", params, new AsyncHttpResponseHandler() {
	        @Override
			public void onFailure(Throwable error, String resp) {
				super.onFailure(error, resp);
				Log.e("async-fail", resp);
			}

			@Override
	        public void onSuccess(String response) {
	            Log.e("async-suc", response);
	        }        
	        
	    });
	    
		return encodedImageStr; 
	}
	
	/** Get data from the Server **/
	
	public static List<Protest> updateFromServer(String time) {

//		String URL = DEV_SITE + ":" + PORT;
		
		List<Protest> protestList = null;
			
		try {
			
			HttpGet httpGet = new HttpGet( URL + "/data" );
			
			HttpClient client = new DefaultHttpClient();
	        // prepare the handler that will handle the response
	        ResponseHandler<String> handler = new BasicResponseHandler(); 
	        // Execute post and get the result
	        HttpResponse response = client.execute( httpGet );   
	        // Get the Object from the response
	        HttpEntity entity = response.getEntity();
	        // Convert the HttpEntity to a readable JSON String
	        String strFormattedData = EntityUtils.toString(entity);
			// Create a new JSON Object with name/value mappings from the JSON formatted string
	        JSONObject jsonResponse = new JSONObject(strFormattedData);
	        // Convert the JSON data to a useable array of protest points
	        protestList = convertJsonToArray(jsonResponse);	        
			Log.e( "HTTP-GET", protestList.toString() );
			
		} catch(JSONException e) {
	    	Log.e( TAG, "Error in http connection " + e.toString() );
			e.printStackTrace();
		} catch (ParseException e) {
	    	Log.e( TAG, "Error in http connection " + e.toString() );
			e.printStackTrace();
		} catch (IOException e) {
	    	Log.e( TAG, "Error in http connection " + e.toString() );
			e.printStackTrace();
		}
		
	    // return the final JSON response
	    return protestList;		
	}
	
	private static List<Protest> convertJsonToArray(JSONObject jsonObject) throws JSONException {
		// This list will hold all the protest points to be created
		List<Protest> protestList = new ArrayList<Protest>();
		// get the ROWS object which is an array of all the protest points
		JSONArray jsonArray = jsonObject.optJSONArray("rows");
		// loop through each object in the json array
		for( int i=0; i < jsonArray.length(); i++ ) {
			// A temporary holder for protest points
			Protest temp_protest = new Protest();
			JSONObject nextJsonObj = jsonArray.getJSONObject(i);
			// convert the type and url of the protest
			temp_protest.setType( nextJsonObj.optString("type") ); // type of event
			temp_protest.setImageUrl( nextJsonObj.optString("image_url") ); // url of the image
			// get the latitude and longitude
			JSONObject geoJsonData = new JSONObject( nextJsonObj.optString("st_asgeojson") );
			double longitude = Double.parseDouble("" + geoJsonData.optJSONArray("coordinates").get(0));
			double latitude = Double.parseDouble("" + geoJsonData.optJSONArray("coordinates").get(1));
//			double longitude = (Double) geoJsonData.optJSONArray("coordinates").get(0);
//			double latitude = (Double) geoJsonData.optJSONArray("coordinates").get(1);
			temp_protest.setLatitude(latitude);
			temp_protest.setLongitude(longitude);
			// add the protest point to the list
			protestList.add(temp_protest);
		}
		// We have our list of protest points!
		return protestList;		
	}
	
	/** Send data to the Server **/

	public static String sendToServer(String encodedImageStr) {
		return sendToServer(encodedImageStr, "0", "0", "");
	}
	
	public static String sendToServer(String encodedImageStr, String latitude, String longitude) {
		return sendToServer(encodedImageStr, latitude, longitude, "");
	}
	
	public static String sendToServer(String encodedImageStr, String latitude, String longitude, String userId) {
		
//		String URL = DEV_SITE + ":" + PORT;
		
		String response = "";
		
		try {
			// Build the JSON object to pass parameters
			JSONObject jsonObj = new JSONObject();
			
			jsonObj.put("user_id", userId);
			jsonObj.put("latitude", latitude);
			jsonObj.put("longitude", longitude);
			// DEBUG Make sure to turn off DEV in production
			if( DEV )	jsonObj.put("image", testImageStr);
			else		jsonObj.put("image", encodedImageStr);
			
			// Set the URL to the specific script we want to run
			// Create the POST object and add the parameters
			HttpPost httpPost = new HttpPost( URL + "/post" );
			
			// Encode the List and insert it into the Post object
			StringEntity entity = new StringEntity(jsonObj.toString(), HTTP.UTF_8);
			entity.setContentType("application/json");
			httpPost.setEntity(entity);
			
			HttpClient client = new DefaultHttpClient();
            // prepare the handler that will handle the response
            ResponseHandler<String> handler = new BasicResponseHandler(); 
            // Execute post and get the result
            response = client.execute( httpPost, handler );	// remove handler if you want to return a HttpResponse object
			
			Log.e("HTTP-POST", response);
            
	    } catch(Exception e) {
	    	Log.e( TAG, "Error in http connection " + e.toString() );
	    }
		
	    //FEEDBACK FOR DEBUGGING
	    Log.e( TAG, "HTTP-POST Response: " + response );
	    // return the final JSON response
	    return response;
	}
	
     
    

	
}
