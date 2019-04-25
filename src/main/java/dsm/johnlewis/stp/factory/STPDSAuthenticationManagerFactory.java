package dsm.johnlewis.stp.factory;

import org.springframework.beans.factory.annotation.Autowired;

import dsm.johnlewis.stp.manager.ISTPAuthentication;

public class STPDSAuthenticationManagerFactory {
	
	@Autowired
	ISTPAuthentication authenticationManager;
	
	public ISTPAuthentication authenticationManager(){
		return authenticationManager;
	}
}
