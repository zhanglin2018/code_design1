package Start;

public class PermissionDenied extends PermissionState {

	public PermissionDenied() {
		super("DENIED");
	}

	public void claimedBy(SystemAdmin admin, SystemPermission permission) {
		return;
	}
	
	public void deniedBy(SystemAdmin admin, SystemPermission permission) {
		return;
	}

	public void grantedBy(SystemAdmin admin, SystemPermission permission) {
		return;
	}
}
