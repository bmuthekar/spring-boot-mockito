package com.dodge.dodgedemoapp.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.dodge.dodgedemoapp.entity.User;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TestEntityManager entityManager; 

	@Test
	public void testFindByUserNameAndPassword() {
		User user = new User("testUser1", "testPassword", "ADMIN");
		entityManager.persist(user);
		
		User fetchedUser = userRepository.findByUserNameAndPassword("testUser1", "testPassword");
		assertEquals(user, fetchedUser);
		userRepository.delete(fetchedUser);		
	}

}
