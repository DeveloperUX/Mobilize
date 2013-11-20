package mobilize.me;

import java.util.ArrayList;
import java.util.List;

public class ProtestMapSingleton {
	
	private List<Protest> protestMap;
	private static ProtestMapSingleton singleton;
	
	public ProtestMapSingleton() {
		protestMap = new ArrayList<Protest>();
		singleton = this;
	}
	
	public static ProtestMapSingleton getInstance() {
		if( singleton == null )
			return new ProtestMapSingleton();
		return singleton;		
	}
	
	public void set(List<Protest> protests) {
		protestMap = protests;
	}
	
	public List<Protest> getProtestMap() {
		return protestMap;
	}

}
