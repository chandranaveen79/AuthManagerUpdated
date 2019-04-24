package dsm.johnlewis.stp.AuthenticationManager;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import dsm.johnlewis.stp.bean.Person;
import dsm.johnlewis.stp.configuration.LDAPServerConfig;
import dsm.johnlewis.stp.exceptions.STPAuthenticationManagerSystemException;
import dsm.johnlewis.stp.exceptions.STPCredentialsExpiredException;
import dsm.johnlewis.stp.exceptions.STPCredentialsIncorrectException;
import dsm.johnlewis.stp.exceptions.STPNewCredentialsNotAcceptedException;
import dsm.johnlewis.stp.repository.PersonRepository;

public class STPAuthentication implements ISTPAuthentication {

	LDAPServerConfig ldapServerConfig = LDAPServerConfig.getconfig();
	private final Logger LOGGER = LoggerFactory.getLogger(STPAuthentication.class);

	public void authenticateUser(String userid, String password) throws STPCredentialsExpiredException,
			STPNewCredentialsNotAcceptedException, STPAuthenticationManagerSystemException {

		/*Iterable<Person> persons = getPersonRepository().findAll();
		if(persons != null) {
			LOGGER.info(persons.toString());
		}*/
		
		if (userid != null) {
			if (ldapServerConfig.getconfiguration() != true) {
				throw new STPCredentialsExpiredException();
			} else {
				if (userid.equals(ldapServerConfig.getUserid()) && password.equals(ldapServerConfig.getPassword())) {

					LOGGER.info("STPAuthentication.authenticateUser()..." + " : " + "user id=> " + userid + " : "
							+ " passord=>  " + ldapServerConfig.getPassword());
				} else {
					throw new STPNewCredentialsNotAcceptedException();
				}

			}
		} else {
			throw new STPAuthenticationManagerSystemException();
		}
	}

	@Override
	public void changepassword(String userid, String oldpassword, String newpassword)
			throws STPCredentialsIncorrectException, STPCredentialsExpiredException,
			STPAuthenticationManagerSystemException {

		if (userid != null) {

			if (ldapServerConfig.getUserid().equals(userid)) {
				if (ldapServerConfig.getPassword().equals(oldpassword)) {

					ldapServerConfig.setPassword(newpassword);

					LOGGER.info("STPAuthentication.authenticateUser()..." + " : " + "user id=> " + userid + " : "
							+ " new passord=>  " + ldapServerConfig.getPassword());

				} else {
					throw new STPCredentialsIncorrectException();
				}
			} else {
				throw new STPCredentialsExpiredException();
			}
		} else {
			throw new STPAuthenticationManagerSystemException();

		}

	}
}
