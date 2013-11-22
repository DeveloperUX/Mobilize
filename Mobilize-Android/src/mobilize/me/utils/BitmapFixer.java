package mobilize.me.utils;

import java.io.ByteArrayOutputStream;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class BitmapFixer {
	
	public int calculateInSampleSize( BitmapFactory.Options options, int reqWidth, int reqHeight) {
		
	    // Raw height and width of image
	    final int height = options.outHeight;
	    final int width = options.outWidth;
	    int inSampleSize = 1;
	
	    if (height > reqHeight || width > reqWidth) {
	    	
	        final int halfHeight = height / 2;
	        final int halfWidth = width / 2;
	
	        // Calculate the largest inSampleSize value that is a power of 2 and keeps both
	        // height and width larger than the requested height and width.
	        do {
	        	inSampleSize *= 2;
	        } while ((halfHeight / inSampleSize) > reqHeight && (halfWidth / inSampleSize) > reqWidth);
	    }
	
	    return inSampleSize;
	}
	
	public Bitmap decodeSampledBitmap(String file, int reqWidth, int reqHeight) {

	    // First decode with inJustDecodeBounds=true to check dimensions
	    final BitmapFactory.Options options = new BitmapFactory.Options();
	    options.inJustDecodeBounds = true;
	    BitmapFactory.decodeFile(file, options);

	    // Calculate inSampleSize
	    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

	    // Decode bitmap with inSampleSize set
	    options.inJustDecodeBounds = false;
	    return BitmapFactory.decodeFile(file, options);
	}
	

    public String compressImage(String path) {
    	// get a bitmap from the image file
		Bitmap bm = BitmapFactory.decodeFile( path );
		// scale down the bitmap to make the file smaller
		
		// STEP 3 :: The image Bitmap from the Bitmap gets compressed as a jpeg 
		// for saving and gets stored into a ByteArrayOutputStream
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// Compress the Bitmap to JPEG
		bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
		// Get a transferrable Byte Array
		byte[] imageByteArray = baos.toByteArray();
		// Convert the Image Bytecode to Base 64 
		return Base64.encodeToString(imageByteArray, Base64.NO_WRAP);
    }

    public String compressImage(Bitmap bm) {
		// The image Bitmap from the Bitmap gets compressed as a jpeg 
		// for saving and gets stored into a ByteArrayOutputStream
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// Compress the Bitmap to JPEG
		bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
		// Get a transferrable Byte Array
		byte[] imageByteArray = baos.toByteArray();
		// Convert the Image Bytecode to Base 64 
		return Base64.encodeToString(imageByteArray, Base64.NO_WRAP);
    }

}
