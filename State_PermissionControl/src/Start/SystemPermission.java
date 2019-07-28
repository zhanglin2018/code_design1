package Start;

//SystemPermission 类使用简单的条件逻辑管理访问软件系统的许可状态
//SystemPermission 中的 state 实例变量会在 request，claimed，denied 和 granted 等状态之间转换
public class SystemPermission {

	private SystemProfile profile;
	private SystemUser requestor;
	private SystemAdmin admin;
	private boolean isGranted;
	private boolean isUnixPermissionGranted;
	private PermissionState state;

	public SystemPermission(SystemUser requestor, SystemProfile profile) {
		this.requestor = requestor;
		this.profile = profile;
		state = PermissionState.REQUESTED;
		
		isGranted = false;
		notifyAdminOfPermissionRequest();
	}


	public PermissionState getState() {
		return state;
	}

	public void setState(PermissionState state) {
		this.state = state;
	}

	public void claimedBy(SystemAdmin admin) {
		if (!state.equals(PermissionState.REQUESTED) && !state.equals(PermissionState.UNIX_REQUESTED))
			return;
		willBeHandledBy(admin);
		if (state.equals(PermissionState.REQUESTED))
			state = PermissionState.CLAIMED;
		else if (state.equals(PermissionState.UNIX_REQUESTED))
			state = PermissionState.UNIX_CLAMED;
	}

	public void deniedBy(SystemAdmin admin) {
		if (!state.equals(PermissionState.CLAIMED) && !state.equals(PermissionState.UNIX_CLAMED))
			return;
		if (!this.admin.equals(admin))
			return;
		isGranted = false;
		isUnixPermissionGranted = false;
		state = PermissionState.DENIED;
		notifyUserOfPermissionRequestResult();
	}

	public void grantedBy(SystemAdmin admin) {
		if (!state.equals(PermissionState.CLAIMED) && !state.equals(PermissionState.UNIX_CLAMED))
			return;
		if (!this.admin.equals(admin))
			return;

		if (profile.isUnixPermissionRequired() && state.equals(PermissionState.UNIX_CLAMED))
			isUnixPermissionGranted = true;
		else if (profile.isUnixPermissionRequired() && !isUnixPermissionGranted()) {
			state = PermissionState.UNIX_REQUESTED;
			notifyUnixAdminsOfPermissionRequest();
			return;
		}
		state = PermissionState.GRANTED;
		isGranted = true;
		notifyUserOfPermissionRequestResult();
	}

	private void notifyAdminOfPermissionRequest() {
		System.out.println("Permission Requested.");
	}

	private void willBeHandledBy(SystemAdmin admin) {
		this.admin = admin;
		System.out.println("Permission Claimed.");
	}

	private void notifyUnixAdminsOfPermissionRequest() {
		System.out.println("UNIX Permission Requested.");
	}

	private void notifyUserOfPermissionRequestResult() {
		if (isGranted)
			System.out.println("Permission Granted.");
		else
			System.out.println("Permission NOT Granted.");
	}

	public PermissionState state() {
		return state;
	}

	public boolean isGranted() {
		return isGranted;
	}

	private boolean isUnixPermissionGranted() {
		return isUnixPermissionGranted;
	}
}
