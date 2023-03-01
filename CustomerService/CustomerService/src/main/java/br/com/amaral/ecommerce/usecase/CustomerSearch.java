/**
 * 
 */
package br.com.amaral.ecommerce.usecase;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.amaral.ecommerce.domain.Customer;
import br.com.amaral.ecommerce.repository.ICustomerRepository;
import br.com.amaral.ecommerce.exception.EntityNotFoundException;

/**
 * @author leandro.amaral
 *
 */

@Service
public class CustomerSearch {

	private ICustomerRepository customerRepository;

	@Autowired
	public CustomerSearch(ICustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public Page<Customer> search(Pageable pageable) {
		return customerRepository.findAll(pageable);
	}

	public Customer searchById(String id) {
		return customerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(Customer.class, "id", id));
	}

	public Boolean isRegistered(String id) {
		Optional<Customer> customer = customerRepository.findById(id);
		return customer.isPresent() ? true : false;
	}

	public Customer searchByCPF(Long cpf) {
		return customerRepository.findByCpf(cpf)
				.orElseThrow(() -> new EntityNotFoundException(Customer.class, "cpf", String.valueOf(cpf)));
	}

}
