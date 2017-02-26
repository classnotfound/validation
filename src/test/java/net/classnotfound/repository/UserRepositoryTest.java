package net.classnotfound.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.classnotfound.TestConfig;
import net.classnotfound.ValidationApplication;
import net.classnotfound.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ValidationApplication.class, TestConfig.class })
public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@PersistenceContext
	private EntityManager em;

	@Test
	public void testSaveNew() {
		User jane = User.builder().lastname("Doe").firstname("Jane").build();
		User saved = userRepository.save(jane);
		Assert.assertNotNull(saved.getId());
	}

	@Test(expected = ConstraintViolationException.class)
	@Transactional
	public void testSaveEmptyLastname() {
		User jane = User.builder().firstname("Jane").build();
		userRepository.save(jane);
		em.flush();
	}

	@Test(expected = ConstraintViolationException.class)
	@Transactional
	public void testSaveWrongLastname() {
		User jane = User.builder().lastname("************").firstname("Jane").build();
		userRepository.save(jane);
		em.flush();
	}

}
