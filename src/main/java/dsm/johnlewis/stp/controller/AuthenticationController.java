package dsm.johnlewis.stp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dsm.johnlewis.stp.exceptions.STPAuthenticationManagerSystemException;
import dsm.johnlewis.stp.exceptions.STPCredentialsExpiredException;
import dsm.johnlewis.stp.exceptions.STPCredentialsIncorrectException;
import dsm.johnlewis.stp.exceptions.STPDSSecuritySystemException;
import dsm.johnlewis.stp.exceptions.STPDSSecurityUserIDNotFound;
import dsm.johnlewis.stp.factory.STPDSAuthenticationManagerFactory;
import dsm.johnlewis.stp.factory.STPDSSecurityFactory;

@RestController
public class AuthenticationController {

	@Autowired
	STPDSAuthenticationManagerFactory authenticationManagerFactory;

	@Autowired
	STPDSSecurityFactory securityFactory;

	@GetMapping(value = "/searchUser", path = "/userId")
	public @ResponseBody String searchUser(@PathVariable("userId") String userId)
			throws STPDSSecurityUserIDNotFound, STPDSSecuritySystemException {
		return securityFactory.securityManager().findDSSecurityUserId(userId);
	}

	@GetMapping(value = "/authenticateUser")
	public @ResponseBody boolean authenticateUser(@PathVariable("username") String username,
			@PathVariable("password") String password) throws STPCredentialsExpiredException,
			STPCredentialsIncorrectException, STPAuthenticationManagerSystemException {
		return authenticationManagerFactory.authenticationManager().authenticateUser(username, password);
	}
}
