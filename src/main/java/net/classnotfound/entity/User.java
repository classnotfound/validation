package net.classnotfound.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity
public class User {

	@Id
	@GeneratedValue(generator = "userSeq")
	@SequenceGenerator(name = "userSeq", sequenceName = "USER_SEQ", allocationSize = 1)
	private Long id;

	@Column
	@Pattern(regexp = "[a-z-A-Z]*", message = "First name has invalid characters")
	private String firstname;

	@Column
	@NotNull(message = "Null value is not allowed for last name")
	@Pattern(regexp = "[a-z-A-Z]*", message = "Last name has invalid characters")
	private String lastname;


}
