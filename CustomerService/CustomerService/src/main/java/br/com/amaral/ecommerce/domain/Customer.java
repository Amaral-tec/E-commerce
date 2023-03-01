package br.com.amaral.ecommerce.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author leandro.amaral
 *
 */
@Document(collection = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "Customer", description = "Customer")
public class Customer {

	@Id
	@Schema(description = "Unique identifier")
	private String id;

	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description = "Name", minLength = 1, maxLength = 50, nullable = false)
	private String name;

	@NotNull
	@Indexed(unique = true, background = true)
	@Schema(description = "CPF", nullable = false)
	private Long cpf;

	@NotNull
	@Schema(description = "MobilePhone", nullable = false)
	private Long mobilePhone;

	@NotNull
	@Size(min = 1, max = 50)
	@Indexed(unique = true, background = true)
	@Schema(description = "Email", minLength = 1, maxLength = 50, nullable = false)
	@Pattern(regexp = ".+@.+\\..+", message = "Invalid email")
	private String email;

	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description = "Address", minLength = 1, maxLength = 50, nullable = false)
	private String address;

	@NotNull
	@Schema(description = "Number", nullable = false)
	private Integer number;

	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description = "District", minLength = 1, maxLength = 50, nullable = false)
	private String district;

	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description = "City", minLength = 1, maxLength = 50, nullable = false)
	private String city;

	@NotNull
	@Size(min = 1, max = 50)
	@Schema(description = "State", minLength = 1, maxLength = 50, nullable = false)
	private String state;

	@NotNull
	@Schema(description = "CEP", nullable = false)
	private Long cep;
}
