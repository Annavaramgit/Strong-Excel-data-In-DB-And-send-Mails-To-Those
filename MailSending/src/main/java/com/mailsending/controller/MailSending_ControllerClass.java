package com.mailsending.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mailsending.model.MailSendingModelClass;
import com.mailsending.service.MailSendingServiceClass;

import jakarta.mail.MessagingException;


/*  --->this application is performs mail sending to all and particular also,
 *  
 *  --->this application is communicating with Import-EcxcelDayaTo-Database application
       For get the mail id's and names
        
   --->  Import-EcxcelDayaTo-Database application is get all the details which are preset  
    	 in	excel sheet(the sheet name is demo) 
*/
@RestController
public class MailSending_ControllerClass {
	
	//this is service class object
	@Autowired
	private MailSendingServiceClass mailSendingServiceClass;
	
	
	//this controller method is for send mail to particular person only
	@PostMapping("/sending/{id}")
	public String MailSendingMethod(@PathVariable long id,@RequestBody MailSendingModelClass
			       mailSendingModelClass ) throws MessagingException {
		
		mailSendingServiceClass.sendingMailMethod(id,mailSendingModelClass);
		
		return "Sending  mails Successfully...";
	}
	
	//this controller method is for send mail to all the persons which are present in the db or excel sheet 
	@PostMapping("/sendingToAll")
	public String mailSendingToAll(@RequestBody  MailSendingModelClass mailSendingModelClass) throws MessagingException {
		mailSendingServiceClass.sendingMailMethodToAll(mailSendingModelClass);
		return "sended to all";
		
	}
/*
@PostMapping("/sendMail")
	public String send(@RequestParam("mail") String mail) throws MessagingException
	{
	
	     mailSendingServiceClass.sendNariMail(mail);
		return "mail sended done...!";
	}
*/
	
}
