/**
 * 
 */
package br.com.amaral.ecommerce.usecase;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.amaral.ecommerce.domain.Customer;
import br.com.amaral.ecommerce.repository.ICustomerRepository;

/**
 * @author leandro.amaral
 *
 */
public class CustomerRegistration {

	private ICustomerRepository customerRepository;

	@Autowired
	public CustomerRegistration(ICustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public Customer save(@Valid Customer customer) {
		return this.customerRepository.insert(customer);
	}

	public Customer update(@Valid Customer customer) {
		return this.customerRepository.save(customer);
	}

	public void remove(String id) {
		this.customerRepository.deleteById(id);
	}

}