package dsm.johnlewis.stp.AuthenticationManager;

public class STPDSAuthenticationManagerFactory {
	
	
	public static ISTPAuthentication authenticateuser(){
		return new STPAuthentication();
	}

}
