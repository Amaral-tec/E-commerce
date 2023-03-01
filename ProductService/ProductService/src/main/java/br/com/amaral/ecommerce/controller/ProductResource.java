/**
 * 
 */
package br.com.amaral.ecommerce.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.amaral.ecommerce.domain.Product;
import br.com.amaral.ecommerce.usecase.ProductSearch;
import io.swagger.v3.oas.annotations.Operation;

/**
 * @author leandro.amaral
 *
 */
@RestController
@RequestMapping(value = "/product")
public class ProductResource {

	private ProductSearch productSearch;

	private ProductRegistration productRegistration;

	@Autowired
	public ProductResource(ProductSearch productSearch, ProductRegistration productRegistration) {
		this.productSearch = productSearch;
		this.productRegistration = productRegistration;
	}

	@GetMapping
	@Operation(summary = "Search a paginated list of products")
	public ResponseEntity<Page<Product>> search(Pageable pageable) {
		return ResponseEntity.ok(productSearch.search(pageable));
	}

	@GetMapping(value = "/status/{status}")
	@Operation(summary = "Searches a paginated list of products by status")
	public ResponseEntity<Product> searchByStatus(@PathVariable(value = "stautus", required = true) String status) {
		return ResponseEntity.ok(productSearch.search(pageable, status));
	}

	@GetMapping(value = "/{code}")
	@Operation(summary = "Searches a paginated list of products by code")
	public ResponseEntity<search> searchByCode(String code) {
		return ResponseEntity.ok(productSearch.searchByCode(code));
	}

	@PostMapping
	@Operation(summary = "Register a product")
	public ResponseEntity<Product> save(@RequestBody @Valid Product product) {
		return ResponseEntity.ok(productRegistration.save(product));
	}

	@PutMapping
	@Operation(summary = "Update Customer")
	public ResponseEntity<Product> update(@RequestBody @Valid Product product) {
		return ResponseEntity.ok(productRegistration.update(product));
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Remove a product by their unique identifier")
	public ResponseEntity<String> remove(@PathVariable(value = "id") String id) {
		productRegistration.remove(id);
		return ResponseEntity.ok("Successfully removed");
	}

}
