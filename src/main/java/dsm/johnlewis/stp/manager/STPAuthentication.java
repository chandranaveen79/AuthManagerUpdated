package dsm.johnlewis.stp.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Service;

import dsm.johnlewis.stp.exceptions.STPAuthenticationManagerSystemException;
import dsm.johnlewis.stp.exceptions.STPCredentialsExpiredException;
import dsm.johnlewis.stp.exceptions.STPCredentialsIncorrectException;
import dsm.johnlewis.stp.exceptions.STPDSSecuritySystemException;
import dsm.johnlewis.stp.exceptions.STPDSSecurityUserIDNotFound;
import dsm.johnlewis.stp.factory.STPDSSecurityFactory;

@Service
public class STPAuthentication implements ISTPAuthentication {

	@Autowired
	LdapTemplate ldapTemplate;

	@Autowired
	STPDSSecurityFactory securityFactory;

	@Value("${spring.ldap.querybase}")
	private String ldapQueryBase;

	public boolean authenticateUser(String userid, String password) throws STPCredentialsExpiredException,
			STPCredentialsIncorrectException, STPAuthenticationManagerSystemException {

		try {
			if (securityFactory.securityManager().findDSSecurityUserId(userid) != null) {
				if (!securityFactory.securityManager().checkIfPasswordExpired(userid)) {
					AndFilter filter = new AndFilter();
					filter.and(new EqualsFilter("sAMAccountName", userid));
					return ldapTemplate.authenticate(ldapQueryBase, filter.encode(), password);
				}
			}
		} catch (STPDSSecuritySystemException | STPDSSecurityUserIDNotFound e) {
			throw new STPAuthenticationManagerSystemException();
		}
		throw new STPCredentialsIncorrectException();
	}

	@Override
	public void changepassword(String userid, String oldpassword, String newpassword)
			throws STPCredentialsIncorrectException, STPCredentialsExpiredException,
			STPAuthenticationManagerSystemException {
	}
}
