package com.bridgelabz.usermanagement.dto;

import lombok.Data;

@Data
public class UpdateWebpagePermission {
	private int webpageId;
	private int userId;
	private boolean addPermission;
	private boolean deletePermission;
	private boolean modifyPermission;
	private boolean readPermission;
}
