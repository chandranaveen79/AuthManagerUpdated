package dsm.johnlewis.stp.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import dsm.johnlewis.stp.manager.ISTPAuthentication;

@Service
@Scope("singleton")
public class STPDSAuthenticationManagerFactory {
	
	@Autowired
	ISTPAuthentication authenticationManager;
	
	public ISTPAuthentication authenticationManager(){
		return authenticationManager;
	}
}
