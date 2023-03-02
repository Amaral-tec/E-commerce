/**
 * 
 */
package br.com.amaral.ecommerce.domain;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

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
public class ProductQuantity {

	@NotNull
	private Product product;
	
	@NotNull
	private Integer quantity;
	
	private BigDecimal amount;
	
	public ProductQuantity() {
		this.quantity = 0;
		this.amount = BigDecimal.ZERO;
	}

	public void add(Integer quantity) {
		this.quantity += quantity;
		BigDecimal newValue = this.product.getValue().multiply(BigDecimal.valueOf(quantity));
		BigDecimal newAmount = this.amount.add(newAmount);
		this.amount = newAmount;
	}
	
	public void remove(Integer quantity) {
		this.quantity -= quantity;
		BigDecimal newValue = this.product.getValue().multiply(BigDecimal.valueOf(quantity));
		this.amount = this.amount.subtract(newValue);
	}
}
