package nl.hu.v1wac.firstapp.webservices;

import java.security.*;
import javax.ws.rs.core.*;

public class MySecurityContext implements SecurityContext {

	private String name;
	private String role;
	private boolean isSecure;

	public MySecurityContext(String name, String role, boolean isSecure) {
		super();
		this.name = name;
		this.role = role;
		this.isSecure = isSecure;
	}

	@Override
	public Principal getUserPrincipal() {
		return new Principal() {
			public String getName() {
				return name;
			}
		};
	}

	@Override
	public boolean isUserInRole(String role) {
		return role.equals(this.role);
	}


	@Override
	public boolean isSecure() {
		return isSecure;
	}

	@Override
	public String getAuthenticationScheme() {
		return "Bearer";
	}

}
