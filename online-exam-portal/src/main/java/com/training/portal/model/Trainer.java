package com.training.portal.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.training.portal.utils.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "trainer")
public class Trainer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message = "Name can't be Empty")
	@Pattern(regexp = "^[a-zA-Z]*$", message = "Please use alphabetic letter for the names")
	private String name;

	@NotBlank(message = "technology is  mandatory")
	private String technology;

	@NotBlank(message = "Mention your location")
	private String location;

	@NotBlank(message = "Enter your contact")
	@Pattern(regexp = "(^[0-9]+$)",message = "Contact must be valid")
	private String contact;

	@Email(message = "Email must be valid")
	private String email;

	@Enumerated(EnumType.STRING)
	private Role role;

	@NotBlank(message = "Mention your unique user name")
	private String userName;

	@NotBlank(message = "Password is mandatory")
	private String password;

}
