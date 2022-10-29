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
@Table
@Entity
public class EdEligDtls {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer edTraceId;

	private String holderName;

	private Long ssn;

	private String planName;

	private String planStatus;

	private LocalDate planStartDate;

	private LocalDate planEndDate;

	private Double benefitAmt;

	private String denialReason;

	@CreationTimestamp
	private LocalDate createdDate;

	@UpdateTimestamp
	private LocalDate updatedDate;

	private String createdBy;
	private String updatedBy;

	@OneToOne(targetEntity = DcCasesEntity.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "case_num")
	private DcCasesEntity casesEntity;

	public EdEligDtls() {
	}

	public EdEligDtls(DcCasesEntity casesEntity) {
		this.casesEntity = casesEntity;
	}

}
