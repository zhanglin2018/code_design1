package Finish_Test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import Finish.PermissionState;
import Finish.SystemAdmin;
import Finish.SystemPermission;
import Finish.SystemProfile;
import Finish.SystemUser;

public class SystemPermissionTest_NoUNIX {

	private SystemPermission permission;
	private SystemUser user;
	private SystemProfile profile;
	private SystemAdmin admin;

	@Before
	public void setUp() throws Exception {
		user = new SystemUser();
		profile = new SystemProfile(false);    // ����Ҫ UNIX Ȩ��
		admin = new SystemAdmin();
		permission = new SystemPermission(user, profile);
	}

	@Test
	public void testClaimedBy() {
		// ֱ�� Claim ����
		// �����CLAIMED ״̬�� isGranted = false
		permission.claimedBy(admin);
		assertEquals("claimed", PermissionState.CLAIMED, permission.getState());
		assertEquals("claimed", false, permission.isGranted());

		// �� Claim �ٽ��� Grant ������
		// �����GRANTED ״̬�� isGranted = true
		permission.grantedBy(admin);
		assertEquals("granted", PermissionState.GRANTED, permission.getState());
		assertEquals("granted", true, permission.isGranted());

		// �ٽ��� Claim ����
		// �����GRANTED ״̬�� isGranted = true
		permission.claimedBy(admin);
		assertEquals("granted", PermissionState.GRANTED, permission.getState());
		assertEquals("granted", true, permission.isGranted());
	}

	@Test
	public void testDeniedBy() {
		// δ Claim ��״̬���� Denied ������
		// �����REQUESTED ״̬�� isGranted = false
		permission.grantedBy(admin);
		assertEquals("requested", PermissionState.REQUESTED, permission.getState());
		assertEquals("not granted", false, permission.isGranted());

		// �� Claim �ٽ��� Denied ������
		// �����DENIED ״̬�� isGranted = false
		permission.claimedBy(admin);
		permission.deniedBy(admin);
		assertEquals("granted", PermissionState.DENIED, permission.getState());
		assertEquals("granted", false, permission.isGranted());
	}

	@Test
	public void testGrantedBy() {
		// δ Claim ��״̬���� Grant ������
		// �����REQUESTED ״̬�� isGranted = false
		permission.grantedBy(admin);
		assertEquals("requested", PermissionState.REQUESTED, permission.getState());
		assertEquals("not granted", false, permission.isGranted());

		// �� Claim �ٽ��� Grant ������
		// �����GRANTED ״̬�� isGranted = true
		permission.claimedBy(admin);
		permission.grantedBy(admin);
		assertEquals("granted", PermissionState.GRANTED, permission.getState());
		assertEquals("granted", true, permission.isGranted());
	}
}
