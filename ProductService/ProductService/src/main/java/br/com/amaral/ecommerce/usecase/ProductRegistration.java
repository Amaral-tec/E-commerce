/**
 * 
 */
package br.com.amaral.ecommerce.usecase;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.amaral.ecommerce.domain.Product;
import br.com.amaral.ecommerce.domain.Product.Status;
import br.com.amaral.ecommerce.repository.IProductRepository;

/**
 * @author leandro.amaral
 *
 */
@Service
public class ProductRegistration {

	private IProductRepository productRepository;

	@Autowired
	public ProductRegistration(IProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Product save(@Valid Product product) {
		product.setStatus(Status.ACTIVE);
		return this.productRepository.insert(product);
	}

	public Product update(@Valid Product product) {
		return this.productRepository.save(product);
	}

	public void remover(String id) {
		Product prod = productRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException(Product.class, "id", id));
		prod.setStatus(Status.INACTIVE);
		this.productRepository.save(prod);
	}

}
