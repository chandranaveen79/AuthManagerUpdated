package dsm.johnlewis.stp.securitymanager;

public class STPDSSecurityFactory {
	
	public static ISTPDSSecurity findDSSecurityUserId(){
		return new STPDSSecurity();
	}

}
