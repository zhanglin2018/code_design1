package Start;

/*
 * 1. transform static final String to static final PermissionState class. 
 */

public class PermissionState {
	private String name;

	public PermissionState(String name) {
		super();
		this.name = name;
	}

	public static final PermissionState REQUESTED = new PermissionRequested();
	public static final PermissionState CLAIMED = new PermissionClaimed();
	public static final PermissionState GRANTED = new PermissionGranted();
	public static final PermissionState DENIED = new PermissionDenied();
	public static final PermissionState UNIX_REQUESTED = new PermissionUnixRequested();
	public static final PermissionState UNIX_CLAMED = new PermissionUnixClaimed();

	@Override
	public String toString() {
		return name;
	}
	
	public void claimedBy(SystemAdmin admin, SystemPermission permission) {
		if (!permission.getState().equals(PermissionState.REQUESTED) && !permission.getState().equals(PermissionState.UNIX_REQUESTED))
			return;
		
		permission.willBeHandledBy(admin);
		if (permission.getState().equals(PermissionState.REQUESTED))
			permission.setState(PermissionState.CLAIMED);
		else if (permission.getState().equals(PermissionState.UNIX_REQUESTED))
			permission.setState(PermissionState.UNIX_CLAMED);
	}
}
