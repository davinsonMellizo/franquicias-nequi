package co.com.nequi.model.product.gateways;

import co.com.nequi.model.product.Product;
import reactor.core.publisher.Mono;

public interface ProductRepository {
    Mono<Product> create(Product product);
    Mono<Product> update(Integer stock);
    Mono<Product> update(String name);
    Mono<Integer> delete(Integer id);
}
