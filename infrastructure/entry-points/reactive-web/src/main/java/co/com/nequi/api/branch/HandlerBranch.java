package co.com.nequi.api.branch;

import co.com.nequi.api.branch.data.BranchDTO;
import co.com.nequi.api.branch.data.BranchMapper;
import co.com.nequi.api.commons.ResponseUtil;
import co.com.nequi.api.commons.ValidatorHandler;
import co.com.nequi.usecase.branch.BranchUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class HandlerBranch {
    private final BranchUseCase useCase;
    private final BranchMapper mapper;
    private final ValidatorHandler validatorHandler;

    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(BranchDTO.class)
                .doOnNext(validatorHandler::validateObject)
                .map(mapper::toEntity)
                .flatMap(useCase::create)
                .flatMap(ResponseUtil::responseOk);
    }

    public Mono<ServerResponse> update(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(BranchDTO.class)
                .doOnNext(validatorHandler::validateObject)
                .map(mapper::toEntity)
                .flatMap(useCase::update)
                .flatMap(ResponseUtil::responseOk);
    }

    public Mono<ServerResponse> find(ServerRequest serverRequest) {
        var idFranchise = Integer.parseInt(serverRequest.queryParam("franchise-id").get());
        return useCase.findBy(idFranchise)
                .flatMap(ResponseUtil::responseOk);
    }

    public Mono<ServerResponse> findAll(ServerRequest serverRequest) {
        var idFranchise = Integer.parseInt(serverRequest.queryParam("franchise-id").get());
        return useCase.findAll(idFranchise)
                .flatMap(ResponseUtil::responseOk);
    }
}
