package dsm.johnlewis.stp.factory;

import org.springframework.beans.factory.annotation.Autowired;

import dsm.johnlewis.stp.manager.ISTPDSSecurity;

public class STPDSSecurityFactory {

	@Autowired
	ISTPDSSecurity securityManager;

	public ISTPDSSecurity securityManager() {
		return securityManager;
	}
}
