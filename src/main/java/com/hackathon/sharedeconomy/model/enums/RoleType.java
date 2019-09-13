package com.hackathon.sharedeconomy.model.enums;

import lombok.Getter;

/**
 * Created by YoungMan on 2019-02-14.
 */

@Getter
public enum RoleType {
	OLD("노인"), YOUNG("청년");

	private String roleExplain;

	RoleType(String roleExplain) {
		this.roleExplain = roleExplain;
	}

	public String getRoleExplain() {
		return roleExplain;
	}

	public static RoleType convertRoleType(String role) {
		if (role.equals(OLD.getRoleExplain())) {
			return OLD;
		} else {
			return YOUNG;
		}
	}
}
