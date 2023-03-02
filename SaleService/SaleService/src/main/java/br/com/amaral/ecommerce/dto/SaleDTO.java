/**
 * 
 */
package br.com.amaral.ecommerce.dto;

import java.time.Instant;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author leandro.amaral
 *
 */
@Getter
@Setter
@AllArgsConstructor
@Builder
public class SaleDTO {
	
	@NotNull
	@Size(min = 2, max = 10)
	private String code;
	
	@NotNull
	private String customerId;
	
	@NotNull
	private Instant dateSale;

}
