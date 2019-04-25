package dsm.johnlewis.stp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dsm.johnlewis.stp.exceptions.STPDSSecuritySystemException;
import dsm.johnlewis.stp.exceptions.STPDSSecurityUserIDNotFound;
import dsm.johnlewis.stp.factory.STPDSSecurityFactory;

@RestController
public class SecurityController {

	@Autowired
	STPDSSecurityFactory securityFactory;

	@GetMapping(value = "/searchUser/{userId}")
	public @ResponseBody String searchUser(@PathVariable("userId") String userName)
			throws STPDSSecurityUserIDNotFound, STPDSSecuritySystemException {
		return securityFactory.securityManager().findDSSecurityUserId(userName);
	}
}
