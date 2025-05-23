package co.com.nequi.r2dbc.franchise;

import co.com.nequi.model.franchise.Franchise;
import co.com.nequi.r2dbc.franchise.data.FranchiseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class FranchiseRepositoryImplTest {
    private FranchiseRepositoryImpl repositoryImp;
    @Mock
    private IFranchiseRepository repository;

    private final ObjectMapper mapper = new ObjectMapperImp();

    @BeforeEach
    void init(){
        repositoryImp = new FranchiseRepositoryImpl(repository, mapper);
    }

    @Test
    void createTest(){
        Mockito.when(repository.save(Mockito.any())).thenReturn(Mono.just(new FranchiseDTO()));

        StepVerifier.create(repositoryImp.create(new Franchise()))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void updateTest(){
        Mockito.when(repository.save(Mockito.any())).thenReturn(Mono.just(new FranchiseDTO()));

        StepVerifier.create(repositoryImp.update(new Franchise()))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void findByTest(){
        Mockito.when(repository.findAll()).thenReturn(Flux.just(new FranchiseDTO()));

        StepVerifier.create(repositoryImp.find())
                .expectNextCount(1)
                .verifyComplete();
    }
}
