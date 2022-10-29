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
@Table(name = "DC_INCOME")
public class DcIncomeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer incomeId;
	// private Integer caseNum;
	private Double monthlySalaryIncome;
	private Double rentIncome;
	private Double propertyIcome;

	@CreationTimestamp
	private LocalDate createdDate;

	@UpdateTimestamp
	private LocalDate updatedDate;

	private String createdBy;
	private String updatedBy;

	@OneToOne(targetEntity = DcCasesEntity.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "case_num")
	private DcCasesEntity casesEntity;

	public DcIncomeEntity() {
	}

	public DcIncomeEntity(DcCasesEntity casesEntity) {
		this.casesEntity = casesEntity;
	}
}
