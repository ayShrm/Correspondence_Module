package in.ayush.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

@Configuration
public class EmailConfig
{
	@Bean
	public SimpleMailMessage emailTemplate()
	{
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("user@gmail.com");
		message.setFrom("admin@gmail.com");
		message.setSubject("Important email");
	    message.setText("Find the attached document..!!");
	    return message;
	}
}