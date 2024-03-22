package com.mailsending.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatabaseData {

	
	private long customer_id;
	private String customer_name;
	private String customer_mail;
	private String customer_adress;
}
