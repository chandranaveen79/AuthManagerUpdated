package dsm.johnlewis.stp.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration
@EnableLdapRepositories
class ApplicationConfig {

	@Value("${spring.ldap.urls}")
	private String ldapURL;

	@Value("${spring.ldap.username}")
	private String ldapUN;

	@Value("${spring.ldap.password}")
	private String ldapPW;

	@Bean
	ContextSource contextSource() {
		LdapContextSource ldapContextSource = new LdapContextSource();
		ldapContextSource.setUrl(ldapURL);
		ldapContextSource.setUserDn(ldapUN);
		ldapContextSource.setPassword(ldapPW);
		return ldapContextSource;
	}

	@Bean
	LdapTemplate ldapTemplate(ContextSource contextSource) {
		return new LdapTemplate(contextSource);
	}
}
