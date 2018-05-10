package com.dodge.dodgedemoapp.restcontroller;

import java.sql.Timestamp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.dodge.dodgedemoapp.entity.InfactRecord;
import com.dodge.dodgedemoapp.service.InfactRecordServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = InfactRecordRestController.class, secure = false)
public class InfactRecordRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private InfactRecordServiceImpl infactRecordServiceMock;

	private InfactRecord infactRecord;

	@Test
	public void testList() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/infact-record"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	public void setUpInfactRecord() {
		infactRecord = new InfactRecord("testSourceId", "testLabel",
				"testCode", new Timestamp(System.currentTimeMillis()),
				"testVersion", new Timestamp(System.currentTimeMillis()));
	}

	@Test
	public void testUpdate() throws Exception {
		setUpInfactRecord();
		ObjectMapper mapper = new ObjectMapper();

		Mockito.when(
				infactRecordServiceMock.save(Mockito.any(InfactRecord.class)))
				.thenReturn(infactRecord);
		mockMvc.perform(
				MockMvcRequestBuilders.put("/api/infact-record")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(infactRecord)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(
						MockMvcResultMatchers.content().string(
								"infact-record has been updated successfully."));

	}

	@Test
	public void testDelete() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.delete("/api/infact-record/{id}", 1))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(
						MockMvcResultMatchers.content().string(
								"infact-record has been deleted successfully."));
	}

}
