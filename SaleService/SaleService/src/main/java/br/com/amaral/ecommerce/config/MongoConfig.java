/**
 * 
 */
package br.com.amaral.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author leandro.amaral
 *
 */
@Configuration
@EnableMongoRepositories(basePackages = "br.com.amaral.ecommerce.repository")
public class MongoConfig {

}
