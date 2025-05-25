package co.com.nequi.r2dbc.branch;

import co.com.nequi.model.branch.Branch;
import co.com.nequi.r2dbc.branch.data.BranchDTO;
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
class BranchRepositoryImplTest {
    private BranchRepositoryImpl repositoryImp;
    @Mock
    private IBranchRepository repository;

    private final ObjectMapper mapper = new ObjectMapperImp();

    @BeforeEach
    void init(){
        repositoryImp = new BranchRepositoryImpl(repository, mapper);
    }

    @Test
    void createTest(){
        Mockito.when(repository.save(Mockito.any())).thenReturn(Mono.just(new BranchDTO()));

        StepVerifier.create(repositoryImp.create(new Branch()))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void updateTest(){
        Mockito.when(repository.save(Mockito.any())).thenReturn(Mono.just(new BranchDTO()));

        StepVerifier.create(repositoryImp.update(new Branch()))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void findByTest(){
        Mockito.when(repository.findBy(Mockito.anyInt())).thenReturn(Flux.just(new BranchDTO()));

        StepVerifier.create(repositoryImp.findBy(1))
                .expectNextCount(1)
                .verifyComplete();
    }
}
