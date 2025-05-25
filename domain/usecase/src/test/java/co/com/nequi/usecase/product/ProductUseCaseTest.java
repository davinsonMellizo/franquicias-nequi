package co.com.nequi.usecase.product;

import co.com.nequi.model.product.Product;
import co.com.nequi.model.product.gateways.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class ProductUseCaseTest {
    @InjectMocks
    private ProductUseCase useCase;
    @Mock
    private ProductRepository repository;

    @Test
    void createTest() {
        Mockito.when(repository.create(Mockito.any())).thenReturn(Mono.just(new Product()));
        StepVerifier.create(useCase.create(new Product()))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void updateTest() {
        Mockito.when(repository.update(Mockito.any())).thenReturn(Mono.just(new Product()));
        StepVerifier.create(useCase.update(new Product()))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void deleteTest(){
        Mockito.when(repository.delete(Mockito.anyInt())).thenReturn(Mono.just(1));
        StepVerifier.create(useCase.delete(1))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void getProductWithLargestStockTest(){
        Mockito.when(repository.getProductWithLargestStock(Mockito.anyInt())).thenReturn(Mono.just(new Product()));
        StepVerifier.create(useCase.getProductWithLargestStock(1))
                .expectNextCount(1)
                .verifyComplete();
    }
}
