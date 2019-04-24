package dsm.johnlewis.stp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import dsm.johnlewis.stp.bean.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

	List<Person> findByFirstname(String firstname); 
}