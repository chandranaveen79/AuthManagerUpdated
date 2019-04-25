package dsm.johnlewis.stp.manager;

import dsm.johnlewis.stp.exceptions.STPAuthenticationManagerSystemException;
import dsm.johnlewis.stp.exceptions.STPCredentialsExpiredException;
import dsm.johnlewis.stp.exceptions.STPCredentialsIncorrectException;

public interface ISTPAuthentication {
	boolean authenticateUser(String userid, String password) throws STPCredentialsExpiredException,
			STPCredentialsIncorrectException, STPAuthenticationManagerSystemException;

	void changepassword(String userid, String oldpassword, String newpassword) throws STPCredentialsIncorrectException,
			STPCredentialsExpiredException, STPAuthenticationManagerSystemException;
}
