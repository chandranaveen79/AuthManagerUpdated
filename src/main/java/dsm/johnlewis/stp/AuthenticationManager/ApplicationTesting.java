package dsm.johnlewis.stp.AuthenticationManager;

import dsm.johnlewis.stp.exceptions.STPAuthenticationManagerSystemException;
import dsm.johnlewis.stp.exceptions.STPCredentialsExpiredException;
import dsm.johnlewis.stp.exceptions.STPCredentialsIncorrectException;
import dsm.johnlewis.stp.exceptions.STPDSSecuritySystemException;
import dsm.johnlewis.stp.exceptions.STPDSSecurityUserIDNotFound;
import dsm.johnlewis.stp.exceptions.STPNewCredentialsNotAcceptedException;
import dsm.johnlewis.stp.securitymanager.STPDSSecurityFactory;

public class ApplicationTesting {

	public static void main(String[] args) {
		
		try {
			STPDSAuthenticationManagerFactory.authenticateuser().authenticateUser("ADSIProv","c@ttl3pr0d");
			STPDSAuthenticationManagerFactory.authenticateuser().changepassword("ADSIProv","c@ttl3pr0d","c@ttl3pr0d");
			STPDSSecurityFactory.findDSSecurityUserId().findDSSecurityUserId("ADSIProv");
		} catch (STPCredentialsExpiredException | STPNewCredentialsNotAcceptedException
				| STPAuthenticationManagerSystemException | STPCredentialsIncorrectException |STPDSSecurityUserIDNotFound |STPDSSecuritySystemException e) {
			e.printStackTrace();
		}
		
	}
        
        
}
