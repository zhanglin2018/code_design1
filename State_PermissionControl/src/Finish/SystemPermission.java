package Finish;

//SystemPermission ��ʹ�ü򵥵������߼�����������ϵͳ�����״̬
//SystemPermission �е� state ʵ���������� request��claimed��denied �� granted ��״̬֮��ת��
public class SystemPermission {

	private SystemProfile profile;
	private SystemUser requestor;
	private SystemAdmin admin;
	private boolean isGranted;
	private boolean isUnixPermissionGranted;
	private PermissionState state;

	public SystemPermission(SystemUser requestor, SystemProfile profile) {
		this.requestor = requestor;
		this.setProfile(profile);
		setState(PermissionState.REQUESTED);
		setGranted(false);
		notifyAdminOfPermissionRequest();
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

	private void notifyAdminOfPermissionRequest() {
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
		if (isGranted())
			System.out.println("Permission Granted.");
		else
			System.out.println("Permission NOT Granted.");
	}

	public boolean isGranted() {
		return isGranted;
	}

	boolean isUnixPermissionGranted() {
		return isUnixPermissionGranted;
	}

	public PermissionState getState() {
		return state;
	}

	void setState(PermissionState state) {
		this.state = state;
	}

	public SystemAdmin getAdmin() {
		return admin;
	}

	public void setAdmin(SystemAdmin admin) {
		this.admin = admin;
	}

	public void setGranted(boolean isGranted) {
		this.isGranted = isGranted;
	}

	public void setUnixPermissionGranted(boolean isUnixPermissionGranted) {
		this.isUnixPermissionGranted = isUnixPermissionGranted;
	}

	public SystemProfile getProfile() {
		return profile;
	}

	public void setProfile(SystemProfile profile) {
		this.profile = profile;
	}
}
