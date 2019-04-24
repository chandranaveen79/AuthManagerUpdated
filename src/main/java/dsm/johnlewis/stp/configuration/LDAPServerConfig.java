package dsm.johnlewis.stp.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

import javax.naming.AuthenticationException;
import javax.naming.AuthenticationNotSupportedException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LDAPServerConfig {

	private static LDAPServerConfig ldapserverconfig;
	private final Logger LOGGER = LoggerFactory.getLogger(LDAPServerConfig.class);

	private String userid;
	private String password;
	private String sid;

	private LDAPServerConfig() {

	}

	public static LDAPServerConfig getconfig() {
		if (ldapserverconfig == null) {
			ldapserverconfig = new LDAPServerConfig();
		}
		return ldapserverconfig;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public boolean getconfiguration() {

		Hashtable<String, String> environment = new Hashtable<String, String>();
		Properties conf = new Properties();
		boolean status = false;

		try {
			InputStream ip = this.getClass().getResourceAsStream("/application.properties");
			conf.load(ip);

			environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			environment.put(Context.PROVIDER_URL, "ldap://MCWS-EUDC2.corpeu.buy.com");
			environment.put(Context.SECURITY_AUTHENTICATION, "simple");
			environment.put(Context.SECURITY_PRINCIPAL, conf.getProperty("spring.ldap.username"));
			environment.put(Context.SECURITY_CREDENTIALS, conf.getProperty("spring.ldap.password"));

			DirContext context = new InitialDirContext(environment);

			if (context.getEnvironment().get(Context.SECURITY_PRINCIPAL) != null) {
				status = true;
				this.setUserid(context.getEnvironment().get(Context.SECURITY_PRINCIPAL).toString());
				this.setPassword(context.getEnvironment().get(Context.SECURITY_CREDENTIALS).toString());
				this.setSid(conf.getProperty("SID"));
			}

			context.close();
		} catch (IOException e) {

			LOGGER.error("The authentication is not supported by the server");
		} catch (AuthenticationNotSupportedException exception) {

			LOGGER.error("The authentication is not supported by the server");

		}

		catch (AuthenticationException exception) {

			LOGGER.error("Incorrect password or username");
		}

		catch (NamingException exception) {

			LOGGER.error("Error when trying to create the context");
		}

		return status;
	}

}
