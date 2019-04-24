package dsm.johnlewis.stp.securitymanager;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dsm.johnlewis.stp.configuration.LDAPServerConfig;
import dsm.johnlewis.stp.exceptions.STPDSSecuritySystemException;
import dsm.johnlewis.stp.exceptions.STPDSSecurityUserIDNotFound;

public class STPDSSecurity implements ISTPDSSecurity {

	LDAPServerConfig ldapServerConfig = LDAPServerConfig.getconfig();

	private final Logger LOGGER = LoggerFactory.getLogger(STPDSSecurity.class);

	@Override
	public String findDSSecurityUserId(String userid) throws STPDSSecurityUserIDNotFound, STPDSSecuritySystemException {

		String result = null;

		if (ldapServerConfig.getconfiguration() != true) {
			throw new STPDSSecurityUserIDNotFound();
		} else {
			if (userid.equals(ldapServerConfig.getUserid())) {

				result = ldapServerConfig.getSid();
				LOGGER.info("STPDSSecurity.findDSSecurityUserId()..." + " : " + "DS Security id=> " + result);
			} else {
				throw new STPDSSecuritySystemException();
			}

		}

		return result;
	}

}
