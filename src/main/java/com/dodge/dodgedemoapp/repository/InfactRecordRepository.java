package com.dodge.dodgedemoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dodge.dodgedemoapp.entity.InfactRecord;

@Repository
public interface InfactRecordRepository extends JpaRepository<InfactRecord, Long>{
	
	public InfactRecord findByRecordId(Long recordId);

}
