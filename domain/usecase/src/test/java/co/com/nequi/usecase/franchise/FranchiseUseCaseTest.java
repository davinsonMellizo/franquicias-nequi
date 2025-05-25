package co.com.nequi.usecase.franchise;

import co.com.nequi.model.franchise.Franchise;
import co.com.nequi.model.franchise.gateways.FranchiseRepository;
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
class FranchiseUseCaseTest {
    @InjectMocks
    private FranchiseUseCase useCase;
    @Mock
    private FranchiseRepository repository;

    @Test
    void createTest() {
        Mockito.when(repository.create(Mockito.any())).thenReturn(Mono.just(new Franchise()));
        StepVerifier.create(useCase.create(new Franchise()))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void updateTest() {
        Mockito.when(repository.update(Mockito.any())).thenReturn(Mono.just(new Franchise()));
        StepVerifier.create(useCase.update(new Franchise()))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void findTest(){
        Mockito.when(repository.find()).thenReturn(Flux.just(new Franchise()));
        StepVerifier.create(useCase.find())
                .expectNextCount(1)
                .verifyComplete();
    }
}
