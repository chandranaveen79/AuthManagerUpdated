package dsm.johnlewis.stp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dsm.johnlewis.stp.bean.Person;
import dsm.johnlewis.stp.repository.PersonRepository;

@RestController
public class TestController {

	@Autowired
	PersonRepository personRepository;

	public PersonRepository getPersonRepository() {
		return personRepository;
	}

	public void setPersonRepository(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@GetMapping(value = "/checkUser")
	public @ResponseBody String priceReductionList() {
		List<Person> person = getPersonRepository().findByFirstname("Satish");
		return null;
	}
}
