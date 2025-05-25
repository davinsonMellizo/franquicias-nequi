package co.com.nequi.api.franchise;

import co.com.nequi.api.branch.data.BranchDTO;
import co.com.nequi.api.branch.data.BranchMapper;
import co.com.nequi.api.commons.ResponseUtil;
import co.com.nequi.api.commons.ValidatorHandler;
import co.com.nequi.api.franchise.data.FranchiseDTO;
import co.com.nequi.api.franchise.data.FranchiseMapper;
import co.com.nequi.usecase.branch.BranchUseCase;
import co.com.nequi.usecase.franchise.FranchiseUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class HandlerFranchise {
    private  final FranchiseUseCase useCase;
    private final FranchiseMapper mapper;
    private final ValidatorHandler validatorHandler;

    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(FranchiseDTO.class)
                .doOnNext(validatorHandler::validateObject)
                .map(mapper::toEntity)
                .flatMap(useCase::create)
                .flatMap(ResponseUtil::responseOk);
    }

    public Mono<ServerResponse> update(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(FranchiseDTO.class)
                .doOnNext(validatorHandler::validateObject)
                .map(mapper::toEntity)
                .flatMap(useCase::update)
                .flatMap(ResponseUtil::responseOk);
    }

    public Mono<ServerResponse> find(ServerRequest serverRequest) {
        return useCase.find()
                .flatMap(ResponseUtil::responseOk);
    }
}
