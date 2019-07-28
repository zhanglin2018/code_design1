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

	public static final PermissionState REQUESTED = new PermissionState("REQUESTED");
	public static final PermissionState CLAIMED = new PermissionState("CLAIMED");
	public static final PermissionState GRANTED = new PermissionState("GRANTED");
	public static final PermissionState DENIED = new PermissionState("DENIED");
	public static final PermissionState UNIX_REQUESTED = new PermissionState("UNIX_REQUESTED");
	public static final PermissionState UNIX_CLAMED = new PermissionState("UNIX_CLAMED");

	@Override
	public String toString() {
		return name;
	}
}
