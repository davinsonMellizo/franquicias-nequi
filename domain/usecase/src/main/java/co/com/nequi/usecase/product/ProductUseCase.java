package co.com.nequi.usecase.product;

import co.com.nequi.model.product.Product;
import co.com.nequi.model.product.gateways.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ProductUseCase {
    private ProductRepository repository;

    public Mono<Product> create(Product product){
        return repository.create(product);
    }

    public Mono<Product> update(Product product){
        return repository.update(product);
    }

    public Mono<Integer> delete(Integer id){
        return repository.delete(id);
    }

    public Mono<Integer> findBy(String franchiseName){
        return repository.findBy(franchiseName);
    }
}
