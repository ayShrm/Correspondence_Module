package in.ayush.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ayush.entity.PlanEntity;

public interface PlanRepo extends JpaRepository<PlanEntity, Serializable> {

	public PlanEntity findByPlanName(String planName);

	// @Query("select distinct(planName) from PlanEntity")
	// public List<String> getAllPlans();

}
