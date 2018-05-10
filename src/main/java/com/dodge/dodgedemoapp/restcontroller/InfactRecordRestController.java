package com.dodge.dodgedemoapp.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dodge.dodgedemoapp.entity.InfactRecord;
import com.dodge.dodgedemoapp.service.InfactRecordServiceImpl;

@RestController
@RequestMapping(("/api"))
public class InfactRecordRestController {

	@Autowired
	private InfactRecordServiceImpl infactRecordServiceImpl;
	
	@GetMapping("/index")
	public String welcome() {
		return "Welcome to Dodge";
	}

	@GetMapping("/infact-record")
	private List<InfactRecord> list() {
		List<InfactRecord> infactRecords = infactRecordServiceImpl.list();
		return infactRecords;
	}

	@GetMapping("/infact-record/{id}")
	private InfactRecord get(@PathVariable("id") Long id) {
		return infactRecordServiceImpl.get(id);
	}

	@PostMapping("/infact-record")
	private ResponseEntity<?> save(@RequestBody InfactRecord infactRecord) {
		infactRecordServiceImpl.save(infactRecord);

		return ResponseEntity.ok().body(
				"New InfactRecord has been saved with ID: "
						+ infactRecord.getRecordId());

	}

	/*---Update a infact-record by id---*/
	@RequestMapping(value="/infact-record", method=RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody InfactRecord infactRecord) {
		infactRecordServiceImpl.update(infactRecord);
		return ResponseEntity.ok().body(
				"infact-record has been updated successfully.");
	}

	/*---Delete a infact-record by id---*/
	@DeleteMapping("/infact-record/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		infactRecordServiceImpl.delete(id);
		return ResponseEntity.ok().body(
				"infact-record has been deleted successfully.");
	}

}
