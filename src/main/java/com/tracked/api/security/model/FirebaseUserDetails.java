package com.tracked.api.security.model;

import com.google.firebase.auth.FirebaseToken;

public class FirebaseUserDetails {
	private FirebaseToken token;

	public FirebaseUserDetails(FirebaseToken token) {
		this.token = token;
	}

	public String getEmail() {
		return token.getEmail();
	}

	public String getIssuer() {
		return token.getIssuer();
	}

	public String getName() {
		return token.getName();
	}

	public String getUid() {
		return token.getUid();
	}

}
