package co.com.nequi.api.franchise;

import co.com.nequi.api.commons.ValidatorHandler;
import co.com.nequi.api.franchise.data.FranchiseDTO;
import co.com.nequi.api.franchise.data.FranchiseMapper;
import co.com.nequi.api.product.HandlerProduct;
import co.com.nequi.api.product.data.ProductDTO;
import co.com.nequi.model.franchise.Franchise;
import co.com.nequi.model.product.Product;
import co.com.nequi.usecase.franchise.FranchiseUseCase;
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
class HandlerFranchiseTest {

    @Mock
    private FranchiseUseCase useCase;
    @Spy
    private FranchiseMapper franchiseMapper = Mappers.getMapper(FranchiseMapper.class);
    @Mock
    private ServerRequest serverRequest;
    private HandlerFranchise handler;
    private final FranchiseDTO franchiseDTO = new FranchiseDTO();

    @BeforeEach
    void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        ValidatorHandler validatorHandler = new ValidatorHandler(validatorFactory.getValidator());
        validatorFactory.close();
        handler = new HandlerFranchise(useCase, franchiseMapper, validatorHandler);
        franchiseDTO.setName("Car");
    }

    @Test
    void exceptionByData(){
        franchiseDTO.setName("");
        Mockito.when(serverRequest.bodyToMono(FranchiseDTO.class)).thenReturn(Mono.just(franchiseDTO));

        StepVerifier.create(handler.create(serverRequest))
                .expectError()
                .verify();
    }

    @Test
    void createTest(){
        Mockito.when(useCase.create(Mockito.any()))
                .thenReturn(Mono.just(new Franchise()));

        Mockito.when(serverRequest.bodyToMono(FranchiseDTO.class)).thenReturn(Mono.just(franchiseDTO));

        StepVerifier.create(handler.create(serverRequest))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void updateTest(){
        Mockito.when(useCase.update(Mockito.any()))
                .thenReturn(Mono.just(new Franchise()));

        Mockito.when(serverRequest.bodyToMono(FranchiseDTO.class)).thenReturn(Mono.just(franchiseDTO));

        StepVerifier.create(handler.update(serverRequest))
                .expectNextCount(1)
                .verifyComplete();
    }
}
