package com.kudi_test.product_service.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Where(clause = "deleted_at = null")
@ToString(callSuper=true)
@Data
public abstract class Audit<ID extends Serializable> implements Identifiable<ID>, Serializable{

	static final long serialVersionUID = 1l;
	
	@Column(name="created_at", nullable = false, updatable = false)
	protected LocalDateTime createdAt;
	
	@Column(name="updated_at")
	protected LocalDateTime updatedAt;
	
	@Column(name="deleted_at")
	protected LocalDateTime deletedAt; 
	
	@Column(name="uuid", unique=true, nullable=false, updatable = false)
	protected String uuid;
	
	@PrePersist
	public void init() {
		setCreatedAt(LocalDateTime.now());
		setUpdatedAt(null);
		setDeletedAt(null);
		
		if(this.uuid == null || this.uuid.trim().isEmpty()) {
			this.uuid = UUID.randomUUID().toString();
		}
			
	}
	
	public void delete() {
		setDeletedAt(LocalDateTime.now());
	}
	
	@PreUpdate
	public void setUpdatedAt() {
		updatedAt = LocalDateTime.now();
	}
	
}
