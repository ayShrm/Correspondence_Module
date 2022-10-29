package in.ayush.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ayush.entity.DcEducationEntity;

public interface DcEducationRepo extends JpaRepository<DcEducationEntity, Serializable> {

	@Query("FROM DcEducationEntity e inner join e.casesEntity c where c.caseNum = :caseNum")
	Optional<DcEducationEntity> findByCaseNum(Long caseNum);

}
