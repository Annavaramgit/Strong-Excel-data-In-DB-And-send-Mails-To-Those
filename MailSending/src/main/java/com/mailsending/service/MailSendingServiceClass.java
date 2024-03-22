package com.mailsending.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMailMessage;
//import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mailsending.dto.DatabaseData;
import com.mailsending.dto.FinalResponseClass;
import com.mailsending.model.MailSendingModelClass;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service

public class MailSendingServiceClass {

	// this is used for communicating with another applicaytion
	@Autowired
	private RestTemplate restTemplate;
	
	//this is used for process the thymeleafe template
	@Autowired
	private TemplateEngine templateEngine;
	
	//this is used for send the mails
	@Autowired
	private JavaMailSender mailSender;

	// this for get the from (the mail id which used to send )from
	// application.properties file
	// @Value("${spring.mail.username}")
   //	private String mailFrom;

	// this url for send mail to all the customers
	private String urlForSendMailsToAll = "http://localhost:8081/fetching";

	// this url for send mail to particular customer
	private String urlForSendMailToPartcularCustomer = "http://localhost:8081/fetching/";

	// this method is for send mail to particular customer only present in the
	// database
	public void sendingMailMethod(long id, MailSendingModelClass mailSendingModelClass) throws MessagingException {

		MimeMessage message = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		DatabaseData databaseData = restTemplate.getForObject(urlForSendMailToPartcularCustomer + id,
				DatabaseData.class);
		FinalResponseClass dt = new FinalResponseClass();
		dt.setDatabaseData(databaseData);

		// these 4 lines of code is for thymeleafe 
		//content is a variable name threre in mail-template.html
		//we are injecting what ever coming from database(mail_id, name) to that content variable
		Context context = new Context();
		context.setVariable("content", mailSendingModelClass.getBody() + "" + dt.getDatabaseData().getCustomer_name());
		context.setVariable("content1",mailSendingModelClass.getBody1());
		String processedString = templateEngine.process("mail-template.html", context);

		//this is for To Mail adress coming from database
		helper.setTo(dt.getDatabaseData().getCustomer_mail());
		
		//this is for setting subject content coming from cleint (through post man)
		helper.setSubject(mailSendingModelClass.getSubject());
		
		//this is for setting body oming from cleint (through post man)
		helper.setText(processedString,true);
		
		//this is perform sent the mails
		mailSender.send(message);

	}

	// this method is for sending mails to all the customers present in the database
	public void sendingMailMethodToAll(MailSendingModelClass mailSendingModelClass) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		Context context=new Context();
		
		
		// Fetch the list of DatabaseData from the other application
		ResponseEntity<DatabaseData[]> response = restTemplate.getForEntity(urlForSendMailsToAll, DatabaseData[].class);
		DatabaseData[] databaseDataArray = response.getBody();
		
		// Iterate over each DatabaseData entry and send email
		for (DatabaseData databaseData : databaseDataArray) {
			// helper.setFrom(mailFrom);
			
			
			context.setVariable("content",mailSendingModelClass.getBody()+" "+databaseData.getCustomer_name());
			context.setVariable("content1",mailSendingModelClass.getBody1());
			
			String processedString = templateEngine.process("mail-template.html", context);

			//setting to mailadress,subject,body in the mail
			helper.setTo(databaseData.getCustomer_mail());
			helper.setSubject(mailSendingModelClass.getSubject());
			helper.setText(processedString,true);
			//sending mails
			mailSender.send(message);
		}
	}
/*
	public void sendNariMail(String to) throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

	//	helper.setText("Monthly review");
		helper.setTo(to);
		helper.setSubject("Review result...!");

		Context cc = new Context();
		cc.setVariable("user", "5.5");

		String process = templateEngine.process("nari-template.html", cc);
		helper.setText(process,true);
		mailSender.send(mimeMessage);
	}
*/
}
