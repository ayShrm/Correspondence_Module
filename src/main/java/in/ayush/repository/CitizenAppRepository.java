package in.ayush.repository;

import java.io.Serializable;

import in.ayush.entity.CitizenAppEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitizenAppRepository extends JpaRepository<CitizenAppEntity, Serializable> {

}
