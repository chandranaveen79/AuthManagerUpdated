package dsm.johnlewis.stp.manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.stereotype.Service;

import dsm.johnlewis.stp.exceptions.STPCredentialsExpiredException;
import dsm.johnlewis.stp.exceptions.STPDSSecuritySystemException;
import dsm.johnlewis.stp.exceptions.STPDSSecurityUserIDNotFound;
import dsm.johnlewis.stp.service.QueryService;

@Service
public class STPDSSecurity implements ISTPDSSecurity {

	@Autowired
	LdapTemplate ldapTemplate;

	@Autowired
	QueryService queryService;

	@Override
	public String findDSSecurityUserId(String userid) throws STPDSSecurityUserIDNotFound, STPDSSecuritySystemException {
		if (ldapTemplate == null) {
			throw new STPDSSecuritySystemException();
		} else {
			LdapQuery query = queryService.getQueryForAccount(userid);
			List<String> users = ldapTemplate.search(query, new UserAttributesMapper());
			if (users != null && users.size() > 0) {
				return users.get(0);
			} else {
				throw new STPDSSecurityUserIDNotFound();
			}
		}
	}

	@Override
	public boolean checkIfPasswordExpired(String userId) throws STPCredentialsExpiredException {
		LdapQuery query = queryService.getQueryForAccount(userId);
		String lastModifiedPassword = ldapTemplate
				.search(query, (AttributesMapper<String>) attrs -> (String) attrs.get("whenChanged").get()).get(0);
		String date = lastModifiedPassword.substring(0, 4) + "-" + lastModifiedPassword.substring(4, 6) + "-"
				+ lastModifiedPassword.substring(6, 8);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(sdf.parse(date));
		} catch (ParseException e) {
		}
		calendar.add(Calendar.DAY_OF_MONTH, 30);

		if (calendar.before(Calendar.getInstance()))
			throw new STPCredentialsExpiredException();
		return false;
	}

	private class UserAttributesMapper implements AttributesMapper<String> {

		private final Logger LOGGER = LoggerFactory.getLogger(STPDSSecurity.class);

		public String mapFromAttributes(Attributes attrs) throws NamingException {
			NamingEnumeration<? extends Attribute> namingEnumeration = attrs.getAll();
			try {
				while (namingEnumeration.hasMore()) {
					Attribute attr = namingEnumeration.next();
					LOGGER.info(attr.getID() + " : " + attr.get());
				}
			} catch (NamingException e) {
			}
			return (String) attrs.get("sAMAccountName").get();
		}
	}
}
