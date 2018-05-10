package com.dodge.dodgedemoapp.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.dodge.dodgedemoapp.repository.InfactRecordRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InfactRecordServiceTest {
	
	@MockBean
	private InfactRecordRepository infactRecordRepositoryMock;
	
	@Test
	public void testList(){
		
	}

}
