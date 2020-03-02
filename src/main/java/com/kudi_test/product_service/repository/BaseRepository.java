package com.kudi_test.product_service.repository;

import com.kudi_test.product_service.entity.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T extends Audit, ID extends Serializable> extends JpaRepository<T, ID> {

	public Optional<T> findByUuid(String uuid);

	@Modifying
	@Query("UPDATE #{#entityName} et set et.deletedAt = ?1 WHERE et.uuid = ?2")
	public int deleteByUuid(LocalDateTime localDateTime, String uuid);
	
	
}