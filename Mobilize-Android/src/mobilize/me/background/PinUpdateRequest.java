package mobilize.me.background;

import mobilize.me.ProtestMapSingleton;
import mobilize.me.ServerAccess;

import com.octo.android.robospice.request.SpiceRequest;

public class PinUpdateRequest extends SpiceRequest<ProtestMapSingleton> {
	
	private ProtestMapSingleton protestList;

	public PinUpdateRequest(ProtestMapSingleton protests) {
		super(ProtestMapSingleton.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ProtestMapSingleton loadDataFromNetwork() throws Exception {
		protestList.set( ServerAccess.updateFromServer("") );
		// TODO Auto-generated method stub
		return protestList;
	}

}
