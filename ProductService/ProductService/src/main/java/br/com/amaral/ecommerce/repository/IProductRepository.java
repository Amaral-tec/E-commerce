/**
 * 
 */
package br.com.amaral.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.amaral.ecommerce.domain.Product;
import br.com.amaral.ecommerce.domain.Product.Status;

/**
 * @author leandro.amaral
 *
 */
@Repository
public interface IProductRepository extends MongoRepository<Product, String>{

	Optional<Product> findByCode(String code);
	
	Page<Product> findAllByStatus(Pageable pageable, Status status);

}
