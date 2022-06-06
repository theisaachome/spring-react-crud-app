package com.royal.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 *
 *@author Isaachome
 */

@Data
public class SignInDto {
	

	@NotEmpty(message = "Email should not be null or empty")
	private String usernameOrEmail;

	@NotNull(message="password should not be null or empty")
	private String password;

}
