package net.student.info.entity;


import java.time.LocalDate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="mystudent")
public class Student {
    
	@Id
   @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
	
	    
    @NotBlank(message = "Name is mandatory")
    @Column(name = "name")
    @Pattern(regexp = "([a-zA-Z]{3,30}\\s*)+", message = "Name is invalid!!")
    private String name;
    
    @NotBlank(message = "Email-id is mandatory")
    @Column(name = "email")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+.com$", message = "Email-id is invalid!!")    
    private String email;

    @NotNull(message = "Please enter birth date")
    @Past(message = "Birth date should be less than current date!!")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;
    
    @NotBlank(message = "Mobile number is mandatory")
    @Column(name = "mobile")
    @Pattern(regexp = "[6-9][0-9]{9}", message = "Mobile number is invalid!!")
    private String mobile;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
    
}