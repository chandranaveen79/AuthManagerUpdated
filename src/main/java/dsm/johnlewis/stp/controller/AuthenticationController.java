package dsm.johnlewis.stp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dsm.johnlewis.stp.exceptions.STPAuthenticationManagerSystemException;
import dsm.johnlewis.stp.exceptions.STPCredentialsExpiredException;
import dsm.johnlewis.stp.exceptions.STPCredentialsIncorrectException;
import dsm.johnlewis.stp.factory.STPDSAuthenticationManagerFactory;

@RestController
public class AuthenticationController {

	@Autowired
	STPDSAuthenticationManagerFactory authenticationManagerFactory;

	@GetMapping(value = "/authenticateUser/{userId}/{password}")
	public @ResponseBody boolean authenticateUser(@PathVariable("userId") String username,
			@PathVariable("password") String pw) throws STPCredentialsExpiredException,
			STPCredentialsIncorrectException, STPAuthenticationManagerSystemException {
		return authenticationManagerFactory.authenticationManager().authenticateUser(username, pw);
	}
}
