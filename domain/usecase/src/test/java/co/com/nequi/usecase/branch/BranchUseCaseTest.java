package co.com.nequi.usecase.branch;

import co.com.nequi.model.branch.Branch;
import co.com.nequi.model.branch.gateways.BranchRepository;
import co.com.nequi.model.product.Product;
import co.com.nequi.usecase.product.ProductUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class BranchUseCaseTest {
    @InjectMocks
    private BranchUseCase useCase;
    @Mock
    private BranchRepository repository;
    @Mock
    private ProductUseCase productUseCase;

    @Test
    void createTest(){
        Mockito.when(repository.create(Mockito.any())).thenReturn(Mono.just(new Branch()));
        StepVerifier.create(useCase.create(new Branch()))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void updateTest(){
        Mockito.when(repository.update(Mockito.any())).thenReturn(Mono.just(new Branch()));
        StepVerifier.create(useCase.update(new Branch()))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void findAllTest(){
        Mockito.when(repository.findBy(Mockito.anyInt())).thenReturn(Flux.just(new Branch()));
        StepVerifier.create(useCase.findAll(1))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void findByTest(){
        Mockito.when(repository.findBy(Mockito.anyInt())).thenReturn(Flux.just(Branch.builder()
                .id(1)
                .build()));
        Mockito.when(productUseCase.getProductWithLargestStock(Mockito.anyInt())).thenReturn(Mono.just(new Product()));
        StepVerifier.create(useCase.findBy(1))
                .expectNextCount(1)
                .verifyComplete();
    }
}
