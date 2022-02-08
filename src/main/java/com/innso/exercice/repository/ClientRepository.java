package com.innso.exercice.repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.innso.exercice.entity.ClientFolder;

@Repository
public interface ClientRepository extends JpaRepository<ClientFolder, Integer>{

	@Query("select case when count(c) > 0 then true else false end  from ClientFolder c where c.reference = :reference")
	Boolean isExistReference(@Param("reference") String reference);
	
	Optional<ClientFolder> findByReference(String reference);
	
	@Query(value = "UPDATE ClientFolder SET reference=:reference WHERE reference = :referenceToModify", nativeQuery = true)
	Integer updateReference(@Param("referenceToModify") String referenceToModify, @Param("reference") String reference);
}
