package in.ayush.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ayush.entity.DcCasesEntity;

public interface DcCasesRepo extends JpaRepository<DcCasesEntity, Serializable> {

}
