package com.nus.sec.model;

import com.nus.base.model.BaseModel;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 13-Jan-2025<br>
 * @Time: 8:48:03 pm<br>
 * @Objective: <br>
 */
public class ApexRoleModel extends BaseModel{
	
		
		private String roleName;	
		
		public ApexRoleModel() {
			super();		
		}

		/**
		 * @return the roleName
		 */
		public String getRoleName() {
			return roleName;
		}

		/**
		 * @param roleName the roleName to set
		 */
		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}
		
		
	}// End of ApexRole
