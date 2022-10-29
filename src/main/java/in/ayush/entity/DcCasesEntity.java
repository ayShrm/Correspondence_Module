package in.ayush.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "DC_CASES")
public class DcCasesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long caseNum;

	// private Integer appId;
	// private Integer planId;

	@CreationTimestamp
	private LocalDate createdDate;

	@UpdateTimestamp
	private LocalDate updatedDate;

	private String createdBy;
	private String updatedBy;

	@OneToOne(targetEntity = CitizenAppEntity.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "app_id")
	private CitizenAppEntity appEntity;

	public DcCasesEntity() {
	}

	public DcCasesEntity(CitizenAppEntity appEntity) {
		this.appEntity = appEntity;
	}

	@OneToOne(targetEntity = PlanEntity.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = true, name = "PLAN_ID")
	private PlanEntity planEntity;

	public DcCasesEntity(PlanEntity planEntity) {
		this.planEntity = planEntity;
	}

}
