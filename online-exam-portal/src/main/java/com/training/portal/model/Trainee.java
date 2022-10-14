package com.training.portal.model;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.training.portal.utils.Role;
import org.springframework.stereotype.Component;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trainee")
public class Trainee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	//@Id
	@Valid
	@NotBlank
	private String Name;
	@NotBlank
	@Transient
	private String trainerName;
	private String technology;
	@NotBlank
	private String location;
	//@ManyToOne
	@OneToOne(cascade=CascadeType.DETACH)
	@JoinColumn(name="trainerId")
	private Trainer trainer;
	@NotBlank
	private String contact;
	@NotBlank
	private String domain;
	@NotNull
	private int passOutYear;
	@NotBlank
	private String education;
	@Enumerated(EnumType.STRING)
	private Role role;

	@Column(unique=true)
	@Pattern(regexp="^[a-zA-Z0-9]{3,}$",message="length must be 3 & No spl Char")
	private String userName;

	@Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",message="Password format")
	private String password;
}
