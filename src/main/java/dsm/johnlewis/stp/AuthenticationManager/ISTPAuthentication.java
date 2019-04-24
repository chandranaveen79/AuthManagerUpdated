package dsm.johnlewis.stp.AuthenticationManager;

import dsm.johnlewis.stp.exceptions.STPAuthenticationManagerSystemException;
import dsm.johnlewis.stp.exceptions.STPCredentialsExpiredException;
import dsm.johnlewis.stp.exceptions.STPCredentialsIncorrectException;
import dsm.johnlewis.stp.exceptions.STPNewCredentialsNotAcceptedException;

public interface ISTPAuthentication {

	public void authenticateUser(String userid, String password)throws STPCredentialsExpiredException, STPNewCredentialsNotAcceptedException,STPAuthenticationManagerSystemException;
	public void changepassword(String userid,String oldpassword, String newpassword)throws STPCredentialsIncorrectException, STPCredentialsExpiredException,STPAuthenticationManagerSystemException;
	

}
