package com.accion.recruitment.security.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
*
* @author Manas
*
*/
public class MultiUserAuthenticationProvider implements AuthenticationProvider  {

	@Override
	public Authentication authenticate(Authentication arg0) throws AuthenticationException {
		return null;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return Boolean.TRUE;
	}

}
