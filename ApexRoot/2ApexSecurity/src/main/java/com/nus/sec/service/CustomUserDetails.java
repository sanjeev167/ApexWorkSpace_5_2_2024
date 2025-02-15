package com.nus.sec.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nus.sec.entities.ApexGroupRole;
import com.nus.sec.entities.ApexUser;
import com.nus.sec.entities.ApexUserGroup;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 13-Jan-2025<br>
 * @Time: 4:13:17 pm<br>
 * @Objective: <br>
 */
public class CustomUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
    private Integer id;
	private String username;
	private String password;
	public String userContext;
	private String email;
	private String usernameInDb;

	private List<GrantedAuthority> authorities;

	public CustomUserDetails(ApexUser apexUser) {
		id = apexUser.getId();
		email = apexUser.getEmail();
		username = apexUser.getEmail();
		password = apexUser.getPwd();
		userContext = apexUser.getUserContext();
		usernameInDb = apexUser.getName();
        //Start taking out user's ROLES and prepare authorities.
		List<ApexUserGroup> apexUserGroupList = apexUser.getListOfApexUserGroup();

		List<ApexGroupRole> apexGroupRoleList = null;
		String roleName;
		String allAssignedGroupRoles = null;
		for (ApexUserGroup apexUserGroup : apexUserGroupList) {
			apexGroupRoleList = apexUserGroup.getApexGroup().getListOfApexGroupRole();
			for (ApexGroupRole apexGroupRole : apexGroupRoleList) {
				roleName = apexGroupRole.getApexRole().getRoleName();
				if (allAssignedGroupRoles == null)
					allAssignedGroupRoles = roleName;
				else
					allAssignedGroupRoles = allAssignedGroupRoles + "," + roleName;
			}
		}
		if(allAssignedGroupRoles!=null)
		authorities = Arrays.stream(allAssignedGroupRoles.split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	
	
	
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

	/**
	 * @return the usernameInDb
	 */
	public String getUsernameInDb() {
		return usernameInDb;
	}

	/**
	 * @param usernameInDb the usernameInDb to set
	 */
	public void setUsernameInDb(String usernameInDb) {
		this.usernameInDb = usernameInDb;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the userContext
	 */
	public String getUserContext() {
		return userContext;
	}

	/**
	 * @param userContext the userContext to set
	 */
	public void setUserContext(String userContext) {
		this.userContext = userContext;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}// End of CustomUserDetails
