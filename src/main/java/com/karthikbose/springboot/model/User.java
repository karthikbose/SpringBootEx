package com.karthikbose.springboot.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class User {

	@Id
	@GeneratedValue
	private int userId;
	
	@Size(min=2, max=10, message="UserName should be between2 to 10 characters.")
	@NotNull
	private String userName;
	
	@Min(value=18, message="Age should be minimum of 18")
	@NotNull
	private int age;
	
	@Past(message="Date of birth cannot be future")
	@NotNull
	private Date dob;
	
	@Email(message= "{validation.user.mail}")
	private String email;
}
