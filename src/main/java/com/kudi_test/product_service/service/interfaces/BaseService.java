package com.kudi_test.product_service.service.interfaces;

import com.kudi_test.product_service.entity.Identifiable;
import com.kudi_test.product_service.exception.EntityDoesNotExistException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<T extends Identifiable<ID>, ID extends Serializable> {

	public Optional<T> getById(ID id);
	
	public T save(T model);
	
	public T update(String uuid, T model) throws EntityDoesNotExistException;
	
	public boolean delete(String uuid);
	
	public Page<T> getAll(Pageable page);
	
	public Optional<T> getByUuid(String uuid);

	//throws exception if model cannot be found
	public T findByUuid(String uuid) throws EntityDoesNotExistException;
	
}
