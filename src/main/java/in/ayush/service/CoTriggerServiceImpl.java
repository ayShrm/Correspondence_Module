package in.ayush.service;

import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.io.ByteStreams;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.ayush.entity.CitizenAppEntity;
import in.ayush.entity.CoTriggerEntity;
import in.ayush.entity.DcCasesEntity;
import in.ayush.entity.EdEligDtls;
import in.ayush.repository.CoTriggerRepo;
import in.ayush.repository.DcCasesRepo;
import in.ayush.repository.EdEligRepo;
import in.ayush.util.ReadFile;

@Service
public class CoTriggerServiceImpl implements CoTriggerService {

	@Autowired
	private CoTriggerRepo trgRepo;

	@Autowired
	private EdEligRepo eligRepo;

	@Autowired
	private DcCasesRepo casesRepo;

	@Autowired
	private EmailServiceImpl emailService;

	@Override
	public void saveReport() throws Exception {
		List<CoTriggerEntity> coTrgEntity = trgRepo.findAll();
		for (CoTriggerEntity entity : coTrgEntity) {
			String trgStatus = entity.getTrgStatus();
			if (trgStatus.equals("Completed")) {
				continue;
			}
			DcCasesEntity casesEntity = entity.getCasesEntity();
			Long caseNum = casesEntity.getCaseNum();
			ByteArrayInputStream pdf = generatePdf(caseNum);
			byte[] bytes = ByteStreams.toByteArray(pdf);
			entity.setCoPdf(bytes);
			trgRepo.save(entity);
		}
	}

	@Override
	public String sendEmail() {
		List<CoTriggerEntity> findAll = trgRepo.findAll();
		for(CoTriggerEntity entity: findAll) {
			byte[] coPdf = entity.getCoPdf();
			CitizenAppEntity appEntity = entity.getCasesEntity().getAppEntity();
			String email = appEntity.getEmail();
			String fullName = appEntity.getFullName();
			String body = ReadFile.readMailBody(fullName, "MAIL_BODY");
			String subject = "Plan Status Report";
			boolean mail = emailService.email(coPdf, email, body, subject);	
			if(!mail) {
				return "Mail Failed";
			}
		}
		
		return "Mail Success";
	}
	
//	@Override
//	public String sendreport(Integer trgId) {
//		Optional<CoTriggerEntity> findById = trgRepo.findById(trgId);
//		byte[] coPdf = findById.get().getCoPdf();
//		CitizenAppEntity appEntity = findById.get().getCasesEntity().getAppEntity();
//		String email = appEntity.getEmail();
//		String fullName = appEntity.getFullName();
//		String body = ReadFile.readMailBody(fullName, "MAIL_BODY");
//		String subject = "Plan Status Report";
//		boolean mail = emailService.email(coPdf, email, body, subject);	
//		if(!mail) {
//			return "Mail Failed";
//		}
//		return "Mail Success";
//	}

	private ByteArrayInputStream generatePdf(Long caseNum) throws Exception {

		List<Optional<EdEligDtls>> eligEntities = eligRepo.findByCaseNum(caseNum);
		Optional<DcCasesEntity> caseEntity = casesRepo.findById(caseNum);
		DcCasesEntity dcCasesEntity = caseEntity.get();
		CitizenAppEntity appEntity = dcCasesEntity.getAppEntity();

		Document document = new Document(PageSize.A4);
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		Paragraph p = new Paragraph("Report", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		PdfPTable table = new PdfPTable(7);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.0f, 1.5f, 1.5f, 1.5f });
		table.setSpacingBefore(10);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		Font font1 = FontFactory.getFont(FontFactory.HELVETICA);
		font1.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Citizen Name", font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan Name", font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan Status", font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan Start Date", font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan End Date", font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Benefit Amount", font1));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Denial Reason", font1));
		table.addCell(cell);

		for (Optional<EdEligDtls> entity : eligEntities) {
			table.addCell(appEntity.getFullName());
			table.addCell(entity.get().getPlanName());

			String planStatus = entity.get().getPlanStatus();
			table.addCell(planStatus);

			LocalDate planStartDate = entity.get().getPlanStartDate();
			table.addCell(planStatus.equals("Approved") ? planStartDate.toString() : "NA");

			LocalDate planEndDate = entity.get().getPlanEndDate();
			table.addCell(planStatus.equals("Approved") ? planEndDate.toString() : "NA");

			Double benefitAmt = entity.get().getBenefitAmt();
			table.addCell(planStatus.equals("Approved") ? benefitAmt.toString() : "NA");

			String denialReason = entity.get().getDenialReason();
			table.addCell(planStatus.equals("Denied") ? denialReason : "NA");
		}

		PdfWriter.getInstance(document, out);
		document.open();
		document.add(p);
		document.add(table);
		document.close();

		return new ByteArrayInputStream(out.toByteArray());
	}

}
