package in.ayush.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ayush.entity.DcChildrenEntity;

public interface DcChildrenRepo extends JpaRepository<DcChildrenEntity, Serializable> {

	@Query("FROM DcChildrenEntity i inner join i.casesEntity c where c.caseNum = :caseNum")
	List<Optional<DcChildrenEntity>> findByCaseNum(Long caseNum);

}
