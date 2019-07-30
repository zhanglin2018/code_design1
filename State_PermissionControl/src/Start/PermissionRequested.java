package Start;

public class PermissionRequested extends PermissionState {

	public PermissionRequested() {
		super("REQUESTED");
	}

	@Override
	public void claimedBy(SystemAdmin admin, SystemPermission permission) {
		permission.willBeHandledBy(admin);
		permission.setState(CLAIMED);
	}

	@Override
	public void deniedBy(SystemAdmin admin, SystemPermission permission) {
		return;
	}

	@Override
	public void grantedBy(SystemAdmin admin, SystemPermission permission) {
		return;
	}

}
