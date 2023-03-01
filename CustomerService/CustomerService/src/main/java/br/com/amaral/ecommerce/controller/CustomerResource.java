/**
 * 
 */
package br.com.amaral.ecommerce.controller;

import javax.validation.Valid;

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

import br.com.amaral.ecommerce.domain.Customer;
import br.com.amaral.ecommerce.usecase.CustomerRegistration;
import br.com.amaral.ecommerce.usecase.CustomerSearch;
import io.swagger.v3.oas.annotations.Operation;

/**
 * @author leandro.amaral
 *
 */
@RestController
@RequestMapping(value = "/customer")
public class CustomerResource {

	private CustomerSearch customerSearch;
	private CustomerRegistration customerRegistration;

	@Autowired
	public CustomerResource(CustomerSearch customerSearch, CustomerRegistration customerRegistration) {
		this.customerSearch = customerSearch;
		this.customerRegistration = customerRegistration;
	}

	@GetMapping
	public ResponseEntity<Page<Customer>> search(Pageable pageable) {
		return ResponseEntity.ok(customerSearch.search(pageable));
	}

	@GetMapping(value = "/{id}")
	@Operation(summary = "Search Customer By Id")
	public ResponseEntity<Customer> searchById(@PathVariable(value = "id", required = true) String id) {
		return ResponseEntity.ok(customerSearch.searchById(id));
	}

	@GetMapping(value = "isRegistered/{id}")
	public ResponseEntity<Boolean> isRegistered(@PathVariable(value = "id", required = true) String id) {
		return ResponseEntity.ok(customerSearch.isRegistered(id));
	}

	@PostMapping
	public ResponseEntity<Customer> save(@RequestBody @Valid Customer customer) {
		return ResponseEntity.ok(customerRegistration.save(customer));
	}

	@GetMapping(value = "/cpf/{cpf}")
	@Operation(summary = "Search By CPF")
	public ResponseEntity<Customer> searchByCPF(@PathVariable(value = "cpf", required = true) Long cpf) {
		return ResponseEntity.ok(customerSearch.searchByCPF(cpf));
	}

	@PutMapping
	@Operation(summary = "Update Customer")
	public ResponseEntity<Customer> update(@RequestBody @Valid Customer customer) {
		return ResponseEntity.ok(customerRegistration.update(customer));
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Remove a customer by their unique identifier")
	public ResponseEntity<String> remove(@PathVariable(value = "id") String id) {
		customerRegistration.remove(id);
		return ResponseEntity.ok("Successfully removed");
	}

}