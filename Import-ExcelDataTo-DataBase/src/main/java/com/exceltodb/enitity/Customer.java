package com.exceltodb.enitity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

	@Id
	private long customer_id;
	@Column
	private String customer_name;
	@Column
	private String customer_mail;
	@Column
	private String customer_adress;
}
