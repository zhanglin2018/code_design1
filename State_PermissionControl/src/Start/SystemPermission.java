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
	
	public void setGranted(boolean isGranted) {
		this.isGranted = isGranted;
	}
	
	public void setUnixPermissionGranted(boolean isUnixPermissionGranted) {
		this.isUnixPermissionGranted = isUnixPermissionGranted;
	}

	public void claimedBy(SystemAdmin admin) {
		state.claimedBy(admin, this);
	}

	public void deniedBy(SystemAdmin admin) {
		state.deniedBy(admin, this);
	}

	public void grantedBy(SystemAdmin admin) {
		state.grantedBy(admin, this);
	}

	void notifyAdminOfPermissionRequest() {
		System.out.println("Permission Requested.");
	}

	void willBeHandledBy(SystemAdmin admin) {
		this.setAdmin(admin);
		System.out.println("Permission Claimed.");
	}

	void notifyUnixAdminsOfPermissionRequest() {
		System.out.println("UNIX Permission Requested.");
	}

	void notifyUserOfPermissionRequestResult() {
		if (isGranted)
			System.out.println("Permission Granted.");
		else
			System.out.println("Permission NOT Granted.");
	}

	public SystemProfile getProfile() {
		return profile;
	}

	public void setProfile(SystemProfile profile) {
		this.profile = profile;
	}

	public PermissionState state() {
		return state;
	}

	public boolean isGranted() {
		return isGranted;
	}

	boolean isUnixPermissionGranted() {
		return isUnixPermissionGranted;
	}


	public SystemAdmin getAdmin() {
		return admin;
	}


	public void setAdmin(SystemAdmin admin) {
		this.admin = admin;
	}
}
