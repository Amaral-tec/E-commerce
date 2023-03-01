/**
 * 
 */
package br.com.amaral.ecommerce.usecase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.amaral.ecommerce.domain.Product;
import br.com.amaral.ecommerce.domain.Product.Status;
import br.com.amaral.ecommerce.repository.IProductRepository;

/**
 * @author leandro.amaral
 *
 */
@Service
public class ProductSearch {

	private IProductRepository productRepository;

	@Autowired
	public ProductSearch(IProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public Page<Product> search(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	public Page<Product> search(Pageable pageable, Status status) {
		return productRepository.findAllByStatus(pageable, status);
	}

	public Product searchByCode(String code) {
		return productRepository.findByCode(code)
				.orElseThrow(() -> new EntityNotFoundException(Product.class, "code", code));
	}
}
