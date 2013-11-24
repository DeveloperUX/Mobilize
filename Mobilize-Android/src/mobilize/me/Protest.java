package mobilize.me;

import android.graphics.Bitmap;

public class Protest {

	private String type;
	private String imageUrl;
	private Bitmap thumbnail;
	private double latitude;
	private double longitude;
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double latitude2) {
		this.latitude = latitude2;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public Bitmap getThumbnail() {
		return thumbnail;
	}
	
	public void setThumbnail(Bitmap thumbnail) {
		this.thumbnail = thumbnail;
	}

}
