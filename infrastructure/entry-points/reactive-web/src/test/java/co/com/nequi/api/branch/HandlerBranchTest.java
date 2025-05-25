package co.com.nequi.api.branch;

import co.com.nequi.api.branch.data.BranchDTO;
import co.com.nequi.api.branch.data.BranchMapper;
import co.com.nequi.api.commons.ValidatorHandler;
import co.com.nequi.model.branch.Branch;
import co.com.nequi.usecase.branch.BranchUseCase;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.server.ServerRequest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class HandlerBranchTest {

    @Mock
    private BranchUseCase useCase;
    @Spy
    private BranchMapper branchMapper = Mappers.getMapper(BranchMapper.class);
    @Mock
    private ServerRequest serverRequest;
    private HandlerBranch handler;
    private final BranchDTO branchDTO = new BranchDTO();

    @BeforeEach
    void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        ValidatorHandler validatorHandler = new ValidatorHandler(validatorFactory.getValidator());
        validatorFactory.close();
        handler = new HandlerBranch(useCase, branchMapper, validatorHandler);
        branchDTO.setName("Car");
    }

    @Test
    void exceptionByData(){
        branchDTO.setName("");
        Mockito.when(serverRequest.bodyToMono(BranchDTO.class)).thenReturn(Mono.just(branchDTO));

        StepVerifier.create(handler.create(serverRequest))
                .expectError()
                .verify();
    }

    @Test
    void createTest(){
        Mockito.when(useCase.create(Mockito.any()))
                .thenReturn(Mono.just(new Branch()));

        Mockito.when(serverRequest.bodyToMono(BranchDTO.class)).thenReturn(Mono.just(branchDTO));

        StepVerifier.create(handler.create(serverRequest))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void updateTest(){
        Mockito.when(useCase.update(Mockito.any()))
                .thenReturn(Mono.just(new Branch()));

        Mockito.when(serverRequest.bodyToMono(BranchDTO.class)).thenReturn(Mono.just(branchDTO));

        StepVerifier.create(handler.update(serverRequest))
                .expectNextCount(1)
                .verifyComplete();
    }
}
