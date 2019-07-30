package Start;

public class PermissionUnixRequested extends PermissionState {

	public PermissionUnixRequested() {
		super("UNIX_REQUESTED");
	}

	public void claimedBy(SystemAdmin admin, SystemPermission permission) {
		permission.willBeHandledBy(admin);
		permission.setState(UNIX_CLAMED);
	}

	public void deniedBy(SystemAdmin admin, SystemPermission permission) {
		return;
	}

	public void grantedBy(SystemAdmin admin, SystemPermission permission) {
		return;
	}
}
