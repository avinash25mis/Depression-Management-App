package com.model.common;


import javax.persistence.*;
import java.io.Serializable;

/**
 * The Class UserRole.
 */
@Entity
@Table(name = "user_role")
public class UserRole implements Serializable {

private static final long serialVersionUID = 7359100507804576543L;


	@Id
	@SequenceGenerator(name = "USER_ROLE_ID", sequenceName = "SEQ_USER_ROLE_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ROLE_ID")
	@Column(name = "user_role_id")
	private Long userRoleId;
	@Column(name = "role_name")
	private String roleName;
	@Column(name = "role_code")
	private String roleCode;


	public Long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
}