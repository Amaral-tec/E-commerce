/**
 * 
 */
package br.com.amaral.ecommerce.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author leandro.amaral
 *
 */
@Document(collection = "sale")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Sale {

	public enum Status {
		STARTED, COMPLETED, CANCELED;

		public static Status getByName(String value) {
			for (Status status : Status.values()) {
				if (status.name().equals(value)) {
					return status;
				}
			}
			return null;
		}
	}

	@Id
	private String id;

	@NotNull
	@Size(min = 2, max = 10)
	@Indexed(unique = true, background = true)
	private String code;

	@NotNull
	private String customerId;

	private Set<ProductQuantity> products;

	private BigDecimal amount;

	@NotNull
	private Instant dateSale;

	@NotNull
	private Status status;

	public Sale() {
		products = new HashSet<>();
	}

	public void addProduct(Product product, Integer quantity) {
		validateStatus();
		Optional<ProductQuantity> op = products.stream()
				.filter(filter -> filter.getProduct().getCode().equals(product.getCode())).findAny();
		if (op.isPresent()) {
			ProductQuantity produtpQtd = op.get();
			produtpQtd.addition(quantity);
		} else {
			ProductQuantity prod = ProductQuantity.builder()
					.product(product)
					.amount(BigDecimal.ZERO)
					.quantity(0)
					.build();
			prod.addition(quantity);
			product.add(prod);
		}
		recalculateTotalSaleAmount();
	}

	public void validateStatus() {
		if (this.status == Status.COMPLETED || this.status == Status.CANCELED) {
			throw new UnsupportedOperationException("IMPOSSIBLE TO CHANGE FINALIZED OR CANCELED SALE");
		}
	}

	public void removeProduct(Product product, Integer quantity) {
		validateStatus();
		Optional<ProductQuantity> op = products.stream()
				.filter(filter -> filter.getProduct().getCode().equals(product.getCode())).findAny();

		if (op.isPresent()) {
			ProductQuantity productpQt = op.get();
			if (productpQt.getQuantity() > quantity) {
				productpQt.remove(quantity);
				recalculateTotalSaleAmount();
			} else {
				product.remove(op.get());
				recalculateTotalSaleAmount();
			}

		}
	}

	public void removeAllProducts() {
		validateStatus();
		products.clear();
		amount = BigDecimal.ZERO;
	}

	public Integer getTotalQuantityProducts() {
		// Sums the getQuantity() quantity of all ProductQuantity objects
		int result = products.stream().reduce(0,
				(partialCountResult, prod) -> partialCountResult + prod.getQuantity(), Integer::sum);
		return result;
	}

	public void recalculateTotalSaleAmount() {
		BigDecimal amount = BigDecimal.ZERO;
		for (ProductQuantity prod : this.products) {
			amount = amount.add(prod.getAmount());
		}
		this.amount = amount;
	}

}
