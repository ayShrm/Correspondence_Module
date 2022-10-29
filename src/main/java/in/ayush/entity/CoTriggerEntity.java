package in.ayush.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table
@Data
public class CoTriggerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer trgId;

	@Lob
	private byte[] coPdf;

	private String trgStatus;

	@OneToOne(targetEntity = DcCasesEntity.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "case_num")
	private DcCasesEntity casesEntity;

	public CoTriggerEntity() {
	}

	public CoTriggerEntity(DcCasesEntity casesEntity) {
		this.casesEntity = casesEntity;
	}
}
