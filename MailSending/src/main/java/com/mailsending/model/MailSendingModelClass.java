package com.mailsending.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailSendingModelClass {
//private long id;
	
private String subject;

private String body;
private String body1;
}
