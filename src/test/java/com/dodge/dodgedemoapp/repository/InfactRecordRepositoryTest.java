package com.dodge.dodgedemoapp.repository;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.dodge.dodgedemoapp.entity.InfactRecord;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@RunWith(SpringRunner.class)
@DataJpaTest
public class InfactRecordRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private InfactRecordRepository infactRecordRepository;
	
	@Test
	public void testFindByRecordId() {
		
		InfactRecord infactRecord = new InfactRecord("testSourceId", "testLabel", "testCode", new Timestamp(System.currentTimeMillis()), "testVersion", new Timestamp(System.currentTimeMillis()));
		
		InfactRecord persistedRecord = entityManager.persist(infactRecord);
		
		InfactRecord fetchedinfactRecord = infactRecordRepository.findByRecordId(persistedRecord.getRecordId());
		assertEquals(infactRecord, fetchedinfactRecord);
	}

}
