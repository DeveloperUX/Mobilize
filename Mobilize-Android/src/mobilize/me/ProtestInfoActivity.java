package mobilize.me;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

public class ProtestInfoActivity extends Activity {

    private ImageLoader imageLoader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        setContentView(R.layout.content_layout);
        
        // Get the incoming Image URL from the Map 
        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(MapActivity.IMAGE_URL);
        Bitmap thumbnailBm = (Bitmap) intent.getParcelableExtra(MapActivity.THUMBNAIL);
        
        // Get a hold on the Image View placeholder
        ImageView imageView = ((ImageView) findViewById(R.id.loaded_image));
        
        // Create the Image loader
        imageLoader = initImageLoader();
        
		// load the image
        DisplayImageOptions options = new DisplayImageOptions.Builder()
	        .resetViewBeforeLoading(false)  // we'll be showing a thumbnail first
	        .cacheOnDisc(false)		// Some images are large and we don't want to take up all the phone's space
            .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
	        .build();
        
        imageView.setImageBitmap( thumbnailBm );        
		imageLoader.displayImage( imageUrl, imageView, options );
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	

	/**
	 * Create global configuration and initialize ImageLoader with this configuration
	 * @return The built Image Loader
	 */
    private ImageLoader initImageLoader() {
    	
        int memoryCacheSize;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
            int memClass = ((ActivityManager) 
                    getSystemService(Context.ACTIVITY_SERVICE))
                    .getMemoryClass();
            memoryCacheSize = (memClass / 8) * 1024 * 256;
        } else {
            memoryCacheSize = 2 * 1024 * 1024;
        }
 
        final ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this).threadPoolSize(5)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .memoryCacheSize(memoryCacheSize)
//                .memoryCache(new FIFOLimitedMemoryCache(memoryCacheSize-1000000))
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())                
                .discCacheExtraOptions(800, 800, CompressFormat.PNG, 0, null)
                .build();
 
        ImageLoader.getInstance().init(config);
        
        return ImageLoader.getInstance();
    }
	
	

}
