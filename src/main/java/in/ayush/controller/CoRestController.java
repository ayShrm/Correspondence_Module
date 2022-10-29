package in.ayush.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ayush.service.CoTriggerService;

@RestController
public class CoRestController {

	@Autowired
	private CoTriggerService trgService;

//	@RequestMapping(value = "/pdfreport", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
//	public ResponseEntity<InputStreamResource> report() throws Exception {
//
//		//byte[] sendReport = trgService.saveReport();
//		InputStream targetStream = ByteSource.wrap(sendReport).openStream();
//
//		var headers = new HttpHeaders();
//		headers.add("Content-Disposition", "inline; filename=statusreport.pdf");
//
//		return ResponseEntity
//				.ok()
//				.headers(headers)
//				.contentType(MediaType.APPLICATION_PDF)
//				.body(new InputStreamResource(targetStream));
//	}
	
//	@GetMapping
//	public ResponseEntity<String> sendNotice() throws Exception{
//		trgService.saveReport();
//		String sendEmail = trgService.sendEmail();
//		return new ResponseEntity<>(sendEmail, HttpStatus.OK);
//	}
	
	@GetMapping
	public ResponseEntity<String> sendreport() throws Exception{
		trgService.saveReport();
		String sendEmail = trgService.sendEmail();
		return new ResponseEntity<>(sendEmail, HttpStatus.OK);
	}
}
