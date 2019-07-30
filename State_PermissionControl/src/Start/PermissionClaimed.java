package Start;

public class PermissionClaimed extends PermissionState {

	public PermissionClaimed() {
		super("CLAIMED");
	}

	public void claimedBy(SystemAdmin admin, SystemPermission permission) {
		return;
	}

	public void deniedBy(SystemAdmin admin, SystemPermission permission) {
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
