package dsm.johnlewis.stp.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import dsm.johnlewis.stp.manager.ISTPDSSecurity;

@Service
@Scope("singleton")
public class STPDSSecurityFactory {

	@Autowired
	ISTPDSSecurity securityManager;

	public ISTPDSSecurity securityManager() {
		return securityManager;
	}
}
