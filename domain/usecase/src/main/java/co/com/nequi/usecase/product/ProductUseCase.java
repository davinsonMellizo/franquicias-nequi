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

    public Mono<Product> updateName(Product product){
        return repository.update(product.getName());
    }

    public Mono<Product> updateStock(Product product){
        return repository.update(product.getName());
    }

    public Mono<Integer> delete(Integer id){
        return repository.delete(id);
    }

    public Mono<Integer> find(String franchiseName){
        return repository.findBy(franchiseName);
    }
}
