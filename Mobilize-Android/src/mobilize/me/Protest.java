package mobilize.me;

public class Protest {

	private String type;
	private String imageUrl;
	private String thumbUrl;
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
	
	public String getThumbUrl() {
		return thumbUrl;
	}
	
	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}

}
