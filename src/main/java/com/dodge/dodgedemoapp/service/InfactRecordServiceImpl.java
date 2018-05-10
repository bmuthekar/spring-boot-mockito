package com.dodge.dodgedemoapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dodge.dodgedemoapp.entity.InfactRecord;
import com.dodge.dodgedemoapp.repository.InfactRecordRepository;

@Service
public class InfactRecordServiceImpl implements InfactRecordService {

	@Autowired
	private InfactRecordRepository infactRecordRepository;

	@Override
	public List<InfactRecord> list() {
		return infactRecordRepository.findAll();
	}

	@Override
	public InfactRecord save(InfactRecord infactRecord) {
		infactRecordRepository.save(infactRecord);
		return infactRecord;
	}

	@Override
	public InfactRecord get(Long recordId) {
		return infactRecordRepository.findByRecordId(recordId);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(InfactRecord infactRecord) {
		infactRecordRepository.save(infactRecord);
	}

}
