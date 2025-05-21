package co.com.nequi.model.product.gateways;

import co.com.nequi.model.product.Product;
import reactor.core.publisher.Mono;

public interface ProductRepository {
    Mono<Product> create(Product product);
    Mono<Product> update(Product product);
    Mono<Integer> delete(Integer id);
    Mono<Integer> findBy(String franchiseName);
}
