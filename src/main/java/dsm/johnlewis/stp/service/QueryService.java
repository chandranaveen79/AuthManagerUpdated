package dsm.johnlewis.stp.service;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.ldap.query.SearchScope;
import org.springframework.stereotype.Service;

@Service
public class QueryService {

	@Value("${spring.ldap.querybase}")
	private String ldapQueryBase;

	public LdapQuery getQueryForAccount(String userId) {
		return query().base(ldapQueryBase).searchScope(SearchScope.ONELEVEL).timeLimit(200).countLimit(221)
				.where("objectclass").is("user").and("sAMAccountName").is(userId);
	}
}
