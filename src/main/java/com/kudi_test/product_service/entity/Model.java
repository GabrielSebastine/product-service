package com.kudi_test.product_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
@ToString(callSuper=true)
public class Model extends Audit<Long>{
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@JsonIgnore
	protected Long id;
}
