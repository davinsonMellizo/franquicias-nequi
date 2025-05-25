package co.com.nequi.api.product;

import co.com.nequi.api.commons.ResponseUtil;
import co.com.nequi.api.commons.ValidatorHandler;
import co.com.nequi.api.product.data.ProductDTO;
import co.com.nequi.api.product.data.ProductMapper;
import co.com.nequi.usecase.product.ProductUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class HandlerProduct {
    private  final ProductUseCase useCase;
    private final ProductMapper mapper;
    private final ValidatorHandler validatorHandler;

    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(ProductDTO.class)
                .doOnNext(validatorHandler::validateObject)
                .map(mapper::toEntity)
                .flatMap(useCase::create)
                .flatMap(ResponseUtil::responseOk);
    }

    public Mono<ServerResponse> update(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(ProductDTO.class)
                .doOnNext(validatorHandler::validateObject)
                .map(mapper::toEntity)
                .flatMap(useCase::update)
                .flatMap(ResponseUtil::responseOk);
    }

    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        var id = Integer.parseInt(serverRequest.queryParam("id").get());
        return useCase.delete(id)
                .flatMap(ResponseUtil::responseOk);
    }
}
