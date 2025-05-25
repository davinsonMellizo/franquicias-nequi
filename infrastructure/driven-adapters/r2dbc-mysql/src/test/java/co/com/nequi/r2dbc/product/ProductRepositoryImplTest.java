package co.com.nequi.r2dbc.product;

import co.com.nequi.model.product.Product;
import co.com.nequi.r2dbc.product.data.ProductDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryImplTest {
    private ProductRepositoryImpl repositoryImp;
    @Mock
    private IProductRepository repository;

    private final ObjectMapper mapper = new ObjectMapperImp();

    @BeforeEach
    void init(){
        repositoryImp = new ProductRepositoryImpl(repository, mapper);
    }

    @Test
    void createTest(){
        Mockito.when(repository.save(Mockito.any())).thenReturn(Mono.just(new ProductDTO()));

        StepVerifier.create(repositoryImp.create(new Product()))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void updateTest(){
        Mockito.when(repository.save(Mockito.any())).thenReturn(Mono.just(new ProductDTO()));

        StepVerifier.create(repositoryImp.update(new Product()))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void deleteTest(){
        Mockito.when(repository.deleteById(Mockito.anyInt())).thenReturn(Mono.empty());

        StepVerifier.create(repositoryImp.delete(1))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void findByTest(){
        Mockito.when(repository.getProductWithLargestStock(Mockito.anyInt())).thenReturn(Mono.just(new ProductDTO()));

        StepVerifier.create(repositoryImp.getProductWithLargestStock(1))
                .expectNextCount(1)
                .verifyComplete();
    }
}
