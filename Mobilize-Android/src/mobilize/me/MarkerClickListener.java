package mobilize.me;

import java.util.HashMap;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SlidingDrawer;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.model.Marker;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.FIFOLimitedMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.SimpleImageLoadingListener;

public class MarkerClickListener implements OnMarkerClickListener {

	SlidingDrawer infobox;
	View boxView;
	CustomImageLoader cImageLoader;
	ImageLoader imageLoader;
	HashMap<String, Protest> protestMap;
	
	public MarkerClickListener(SlidingDrawer markerInfobox) {
		infobox = markerInfobox;
        // Create the Map and Image loader
        imageLoader = ImageLoader.getInstance();
	}
	
	@Override
	public boolean onMarkerClick(Marker marker) {
		// Getting reference to the TextView to set title
		
//		TextView title = (TextView) infobox.findViewById(R.id.description);                       
		ImageView imageView = ((ImageView) infobox.findViewById(R.id.loaded_image));
		
//		  title.setText( marker.getTitle() );
		  
		  // load the image
//		  imageLoader.displayImage( protestMap.get( marker.getId() ).getImageUrl(), imageView, new SimpleImageLoadingListener() {
//		      @Override
//		      public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//		          super.onLoadingComplete(imageUri, view, loadedImage);
//		          getInfoContents(marker);
//		      }
//		  });
//		  
      	imageLoader.displayImage( protestMap.get( marker.getId() ).getImageUrl(), imageView);
		
		// if the Infobox is already showing hide it        
        if( infobox.isShown() )
        	infobox.setVisibility(LinearLayout.GONE);
        // else show it
        else
        	infobox.setVisibility(LinearLayout.VISIBLE);
		return false;
	}


}
