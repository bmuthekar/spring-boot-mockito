package com.dodge.dodgedemoapp.restcontroller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dodge.dodgedemoapp.entity.User;
import com.dodge.dodgedemoapp.model.LoginSuccess;
import com.dodge.dodgedemoapp.repository.UserRepository;

@RestController
@RequestMapping("api")
public class LoginController {
	@Autowired
	private UserRepository userRepository;

	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
	public LoginSuccess login(@RequestBody User user) {
		LoginSuccess loginSuccess = new LoginSuccess();
		User userFromDb = userRepository.findByUserNameAndPassword(
				user.getUserName(), user.getPassword());
		if (userFromDb == null) {
			loginSuccess.setSuccess(false);
			loginSuccess.setMessage("Invalid User");
		} else {
			loginSuccess.setSuccess(true);
			loginSuccess.setMessage("User logged in successful");
		}
		return loginSuccess;
	}

	@PostMapping("/save-user")
	public ResponseEntity<?> save(@RequestBody User user) {
		userRepository.save(user);
		URI uri = null;
		try {
			uri = new URI("/api/save-user");
		} catch (URISyntaxException e) {
		}

		return ResponseEntity.created(uri).body(
				"New User has been saved with ID: " + user.getUserName());
	}

	@DeleteMapping("/delete-user")
	public ResponseEntity<?> delete(@RequestBody User user) {
		userRepository.delete(user);
		return ResponseEntity.ok().body(
				"User has been deleted with ID: " + user.getUserName());
	}

}
