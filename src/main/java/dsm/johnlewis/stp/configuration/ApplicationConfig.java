package dsm.johnlewis.stp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration
@EnableLdapRepositories
class ApplicationConfig {

	@Bean
	ContextSource contextSource() {
		LdapContextSource ldapContextSource = new LdapContextSource();
		ldapContextSource.setUrl("ldap://MCWS-EUDC2.corpeu.buy.com");
		ldapContextSource.setUserDn("ADSIProv");
		ldapContextSource.setPassword("c@ttl3pr0d");
		return ldapContextSource;
	}

	@Bean
	LdapTemplate ldapTemplate(ContextSource contextSource) {
		return new LdapTemplate(contextSource);
	}
}
