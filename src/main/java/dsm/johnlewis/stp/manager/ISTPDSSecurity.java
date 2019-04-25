package dsm.johnlewis.stp.manager;

import dsm.johnlewis.stp.exceptions.STPCredentialsExpiredException;
import dsm.johnlewis.stp.exceptions.STPDSSecuritySystemException;
import dsm.johnlewis.stp.exceptions.STPDSSecurityUserIDNotFound;

public interface ISTPDSSecurity {
	String findDSSecurityUserId(String userId) throws STPDSSecurityUserIDNotFound, STPDSSecuritySystemException;
	boolean checkIfPasswordExpired(String userId) throws STPCredentialsExpiredException;
}
