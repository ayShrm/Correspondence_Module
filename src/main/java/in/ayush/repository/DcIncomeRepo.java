package in.ayush.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import in.ayush.entity.DcIncomeEntity;

public interface DcIncomeRepo extends JpaRepository<DcIncomeEntity, Serializable> {

	@Query("FROM DcIncomeEntity i inner join i.casesEntity c where c.caseNum = :caseNum")
	Optional<DcIncomeEntity> findByCaseNum(Long caseNum);

}
