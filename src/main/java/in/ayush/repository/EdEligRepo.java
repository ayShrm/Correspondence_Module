package in.ayush.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ayush.entity.EdEligDtls;

public interface EdEligRepo extends JpaRepository<EdEligDtls, Serializable> {
	
	@Query("FROM EdEligDtls i inner join i.casesEntity c where c.caseNum = :caseNum")
	List<Optional<EdEligDtls>> findByCaseNum(Long caseNum);

}
