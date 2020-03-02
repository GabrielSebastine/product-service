package com.kudi_test.product_service.service;

import com.kudi_test.product_service.entity.Audit;
import com.kudi_test.product_service.exception.EntityDoesNotExistException;
import com.kudi_test.product_service.repository.BaseRepository;
import com.kudi_test.product_service.service.interfaces.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public abstract class BaseServiceImpl<T extends Audit<ID>, ID extends Serializable> implements BaseService<T, ID> {
	
	private Logger log = LoggerFactory.getLogger(this.getClass().getName());
	
	private BaseRepository<T, ID> repository;
	
	public BaseServiceImpl(BaseRepository<T, ID> repository) {
		this.repository = repository;
	}
	
	@Override
	public Optional<T> getById(ID id) {
		return repository.findById(id);
	}

	@Override
	public T save(@NotNull(message ="Cannot save null model")T model) {
		return repository.save(model);
	}
	
	@Override
	public T update(String uuid, @NotNull(message ="Cannot update null model")T model) throws EntityDoesNotExistException {

		Optional<T> storedModel = this.getByUuid(uuid);
		if( !storedModel.isPresent()) {
			log.info("Tried to update an entity but the entity cannot be found, {}.", model);
			throw new EntityDoesNotExistException("The entity could not be found for update.");
		}
		T fetchedModel = storedModel.get();
		model.setCreatedAt(fetchedModel.getCreatedAt());
		model.setUuid(fetchedModel.getUuid());
		model.setId(fetchedModel.getId());
		return this.save(model);
		
	}

	@Override
	public boolean delete(String uuid) {
		int count = repository.deleteByUuid(LocalDateTime.now(), uuid);
		log.info("{} Entity with uuid {} was just deleted", count, uuid);
		return count > 0;
	}

	@Override
	public Page<T> getAll(Pageable pageRequest) {
		
		return repository.findAll(pageRequest);
	}

	@Override
	public Optional<T> getByUuid(String uuid) {
		return repository.findByUuid(uuid);
	}

	@Override
	public T findByUuid(String uuid) throws EntityDoesNotExistException {
		Optional<T> model = this.getByUuid(uuid);
		if( !model.isPresent()) {
			throw new EntityDoesNotExistException("The requested entity does not exist.");
		}
		return model.get();
	}

}
