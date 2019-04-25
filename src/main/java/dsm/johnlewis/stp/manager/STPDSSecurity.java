package dsm.johnlewis.stp.manager;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.util.List;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.SearchScope;

import dsm.johnlewis.stp.exceptions.STPDSSecuritySystemException;
import dsm.johnlewis.stp.exceptions.STPDSSecurityUserIDNotFound;

public class STPDSSecurity implements ISTPDSSecurity {

	@Autowired
	LdapTemplate ldapTemplate;

	@Value("${spring.ldap.querybase}")
	private String ldapQueryBase;

	@Override
	public String findDSSecurityUserId(String userid) throws STPDSSecurityUserIDNotFound, STPDSSecuritySystemException {
		if (ldapTemplate == null) {
			throw new STPDSSecuritySystemException();
		} else {
			LdapQuery query = query().base(ldapQueryBase).searchScope(SearchScope.ONELEVEL).timeLimit(200)
					.countLimit(221).where("objectclass").is("user").and("sAMAccountName").is(userid);
			List<String> users = ldapTemplate.search(query, new UserAttributesMapper());
			if (users != null && users.size() > 0) {
				return users.get(0);
			} else {
				throw new STPDSSecurityUserIDNotFound();
			}
		}
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
