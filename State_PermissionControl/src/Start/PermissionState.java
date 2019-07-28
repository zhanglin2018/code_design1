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
		if (!permission.getState().equals(REQUESTED) && !permission.getState().equals(UNIX_REQUESTED))
			return;

		permission.willBeHandledBy(admin);

		if (permission.getState().equals(REQUESTED))
			permission.setState(CLAIMED);
		else if (permission.getState().equals(UNIX_REQUESTED))
			permission.setState(UNIX_CLAMED);
	}
	
	public void deniedBy(SystemAdmin admin, SystemPermission permission) {
		if (!permission.getState().equals(CLAIMED) && !permission.getState().equals(UNIX_CLAMED))
			return;
		
		if (!permission.getAdmin().equals(admin))
			return;
		
		permission.setGranted(false);
		permission.setUnixPermissionGranted(false);
		permission.setState(DENIED);
		permission.notifyUserOfPermissionRequestResult();
	}

	public void grantedBy(SystemAdmin admin, SystemPermission permission) {
		if (!permission.getState().equals(CLAIMED) && !permission.state().equals(UNIX_CLAMED))
			return;
		if (!permission.getAdmin().equals(admin))
			return;

		if (permission.getProfile().isUnixPermissionRequired() && permission.getState().equals(UNIX_CLAMED))
			permission.setUnixPermissionGranted(true);
		else if (permission.getProfile().isUnixPermissionRequired() && !permission.isUnixPermissionGranted()) {
			permission.setState(UNIX_REQUESTED);
			permission.notifyUnixAdminsOfPermissionRequest();
			return;
		}
		permission.setState(GRANTED);
		permission.setGranted(true);
		permission.notifyUserOfPermissionRequestResult();
	}
}
