package in.ayush.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ayush.entity.CoTriggerEntity;

public interface CoTriggerRepo extends JpaRepository<CoTriggerEntity, Serializable> {

	@Query("FROM CoTriggerEntity i inner join i.casesEntity c where c.caseNum = :caseNum")
	Optional<CoTriggerEntity> findByCaseNum(Long caseNum);

}
