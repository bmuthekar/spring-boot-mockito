package com.dodge.dodgedemoapp.service;

import java.util.List;

import com.dodge.dodgedemoapp.entity.InfactRecord;

public interface InfactRecordService {

	public List<InfactRecord> list();

	public InfactRecord save(InfactRecord infactRecord);

	public InfactRecord get(Long recordId);

	public void delete(long id);

	public void update(InfactRecord infactRecord);

}
