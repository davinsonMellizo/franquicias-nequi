package co.com.nequi.api.product;

import co.com.nequi.api.commons.ValidatorHandler;
import co.com.nequi.api.product.data.ProductDTO;
import co.com.nequi.api.product.data.ProductMapper;
import co.com.nequi.model.product.Product;
import co.com.nequi.usecase.product.ProductUseCase;
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
class HandlerProductTest {

    @Mock
    private ProductUseCase useCase;
    @Spy
    private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
    @Mock
    private ServerRequest serverRequest;
    private HandlerProduct handler;
    private final ProductDTO productDTO = new ProductDTO();

    @BeforeEach
    void setUp() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        ValidatorHandler validatorHandler = new ValidatorHandler(validatorFactory.getValidator());
        validatorFactory.close();
        handler = new HandlerProduct(useCase, productMapper, validatorHandler);
        productDTO.setName("Car");
        productDTO.setStock(1);
    }

    @Test
    void exceptionByData(){
        productDTO.setName("");
        Mockito.when(serverRequest.bodyToMono(ProductDTO.class)).thenReturn(Mono.just(productDTO));

        StepVerifier.create(handler.create(serverRequest))
                .expectError()
                .verify();
    }

    @Test
    void createTest(){
        Mockito.when(useCase.create(Mockito.any()))
                .thenReturn(Mono.just(new Product()));

        Mockito.when(serverRequest.bodyToMono(ProductDTO.class)).thenReturn(Mono.just(productDTO));

        StepVerifier.create(handler.create(serverRequest))
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void updateTest(){
        Mockito.when(useCase.update(Mockito.any()))
                .thenReturn(Mono.just(new Product()));

        Mockito.when(serverRequest.bodyToMono(ProductDTO.class)).thenReturn(Mono.just(productDTO));

        StepVerifier.create(handler.update(serverRequest))
                .expectNextCount(1)
                .verifyComplete();
    }
}
