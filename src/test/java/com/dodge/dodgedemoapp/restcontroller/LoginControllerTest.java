package com.dodge.dodgedemoapp.restcontroller;

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
import org.springframework.web.context.WebApplicationContext;

import com.dodge.dodgedemoapp.entity.User;
import com.dodge.dodgedemoapp.model.LoginSuccess;
import com.dodge.dodgedemoapp.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = LoginController.class)
public class LoginControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private UserRepository userRepositoryMock;

	@Test
	public void testIsValidLogin() throws Exception {
		User user = new User("testUser11", "testPassword", "ADMIN");
		LoginSuccess loginSuccess = new LoginSuccess();
		loginSuccess.setSuccess(true);
		loginSuccess.setMessage("User logged in successful");

		ObjectMapper mapper = new ObjectMapper();

		Mockito.when(
				userRepositoryMock.findByUserNameAndPassword("testUser11",
						"testPassword")).thenReturn(user);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/api/login")
						.content(mapper.writeValueAsString(user))
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(
						MockMvcResultMatchers.content().json(
								mapper.writeValueAsString(loginSuccess)));
	}

	@Test
	public void testIsNotValidLogin() throws Exception {
		User user = new User("testUser11", "testPassword", "ADMIN");
		LoginSuccess loginSuccess = new LoginSuccess();
		loginSuccess.setSuccess(false);
		loginSuccess.setMessage("Invalid User");

		ObjectMapper mapper = new ObjectMapper();

		Mockito.when(
				userRepositoryMock.findByUserNameAndPassword("wrongUser",
						"wrongPassword")).thenReturn(null);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/api/login")
						.content(mapper.writeValueAsString(user))
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(
						MockMvcResultMatchers.content().json(
								mapper.writeValueAsString(loginSuccess)));
	}

	@Test
	public void testSave() throws Exception {
		User user = new User("testUser", "testPassword", "ADMIN");
		ObjectMapper mapper = new ObjectMapper();

		Mockito.when(userRepositoryMock.save(Mockito.any(User.class)))
				.thenReturn(user);
		mockMvc.perform(
				MockMvcRequestBuilders.post("/api/save-user")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(user)))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.status().isCreated());

		Mockito.verify(userRepositoryMock, Mockito.times(1)).save(user);

	}

	@Test
	public void testDelete() throws JsonProcessingException, Exception {
		User user = new User("testUser", "testPassword", "ADMIN");
		ObjectMapper mapper = new ObjectMapper();

		mockMvc.perform(
				MockMvcRequestBuilders.delete("/api/delete-user")
						.contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(user))).andExpect(
				MockMvcResultMatchers.status().isOk());

		userRepositoryMock.delete(user);
	}

}
