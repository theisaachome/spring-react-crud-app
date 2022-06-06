package com.royal.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 *
 * @author Isaachome
 */

@Data
public class SignUpDto {

	@NotNull
	@Size(min = 5, message = "User name should have at least 5 characters")
	private String username;
	
	@NotEmpty(message = "Email should not be null or empty")
	@Email
	private String email;

	@NotNull
	@Size(min = 8, message = "Password  should have at least 8 characters")
	private String password;

}
