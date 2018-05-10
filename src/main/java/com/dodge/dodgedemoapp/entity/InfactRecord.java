package com.dodge.dodgedemoapp.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "infact_record")
public class InfactRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2724044788237684196L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recordSequence")
	@SequenceGenerator(name = "recordSequence", sequenceName = "record_id_seq")
	@Column(name="record_id")
	private Long recordId;

	@Column(name="source_id")
	private String sourceId;

	@Column(name="pref_label")
	private String prefLabel;

	@Column(name="code")
	private String code;

	@Column(name="last_pdated")
	private Timestamp lastUpdated;

	@Column(name="version")
	private String version;

	@Column(name="date_created")
	private Timestamp dateCreated;
	
	public InfactRecord() {
		
	}

	public InfactRecord(String sourceId, String prefLabel,
			String code, Timestamp lastUpdated, String version,
			Timestamp dateCreated) {
		this.sourceId = sourceId;
		this.prefLabel = prefLabel;
		this.code = code;
		this.lastUpdated = lastUpdated;
		this.version = version;
		this.dateCreated = dateCreated;
	}

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getPrefLabel() {
		return prefLabel;
	}

	public void setPrefLabel(String prefLabel) {
		this.prefLabel = prefLabel;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Timestamp getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Timestamp lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

}
