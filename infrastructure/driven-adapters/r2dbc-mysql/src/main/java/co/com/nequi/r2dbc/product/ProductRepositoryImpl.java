package co.com.nequi.r2dbc.product;

import co.com.nequi.model.product.Product;
import co.com.nequi.model.product.gateways.ProductRepository;
import co.com.nequi.r2dbc.helper.ReactiveAdapterOperations;
import co.com.nequi.r2dbc.product.data.ProductDTO;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class ProductRepositoryImpl extends ReactiveAdapterOperations<Product, ProductDTO, Integer, IProductRepository>
        implements ProductRepository {

    public ProductRepositoryImpl(IProductRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Product.class));
    }

    @Override
    public Mono<Product> create(Product product) {
        return Mono.just(product)
                .map(this::toData)
                .flatMap(repository::save)
                .map(this::toEntity);
    }

    @Override
    public Mono<Product> update(Product product) {
        return Mono.just(product)
                .map(this::toData)
                .flatMap(repository::save)
                .map(this::toEntity);
    }

    @Override
    public Mono<Integer> delete(Integer id) {
        return repository.deleteById(id)
                .thenReturn(id);
    }

    @Override
    public Mono<Product> getProductWithLargestStock(Integer idBranch) {
        return repository.getProductWithLargestStock(idBranch)
                .map(this::toEntity);
    }
}
