/**
 * 
 */
package br.com.amaral.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.amaral.ecommerce.domain.Customer;

/**
 * @author leandro.amaral
 *
 */

@Repository
public interface ICustomerRepository<extendes> extends MongoRepository<Customer, String> {

	Optional<Customer> findByCpf(Long cpf);

}
