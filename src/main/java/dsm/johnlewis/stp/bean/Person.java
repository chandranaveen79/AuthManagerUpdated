package dsm.johnlewis.stp.bean;

import javax.lang.model.element.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;
import org.springframework.ldap.odm.annotations.Transient;

@Entry(objectClasses = { "user" }, base = "ou=JLP")
public class Person {

	@Id
	private Name dn;

	@Attribute(name = "Name")
	@DnAttribute(value = "Name", index = 1)
	private String fullName;

	@Attribute(name = "GivenName")
	private String firstName;

	@Attribute(name = "Surname")
	private String lastName;

	@DnAttribute(value = "ou", index = 0)
	@Transient
	private String company;

}
