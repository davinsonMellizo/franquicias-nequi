package co.com.nequi.api.commons;

import co.com.nequi.model.exceptions.TechnicalException;
import co.com.nequi.model.exceptions.TechnicalExceptionEnum;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;


@Log4j2
@Component
@Order(-2)
public class ExceptionHandler extends AbstractErrorWebExceptionHandler {

    public ExceptionHandler(ErrorAttributes errorAttributes, WebProperties webProperties,
                            ApplicationContext applicationContext,
                            ServerCodecConfigurer configurator) {
        super(errorAttributes, webProperties.getResources(), applicationContext);
        this.setMessageWriters(configurator.getWriters());
    }


    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
        var throwable = getError(request);

        return Mono.just(request)
                .map(this::getError)
                .flatMap(Mono::error)
                .onErrorResume(TechnicalException.class, this::responseTechnical)
                .onErrorResume(this::responseAnyError)
                .cast(Error.class)
                .map(errorResponse -> errorResponse.toBuilder()
                        .domain(request.path())
                        .build())
                .doAfterTerminate(() -> log.error(throwable))
                .flatMap(ResponseUtil::responseFail);
    }

    private Mono<Error> responseTechnical(TechnicalException ex) {
        return Mono.just(Error.builder()
                .reason(ex.getMessage())
                .code(ex.getException().getCode())
                .message(ex.getException().getMessage())
                .build()
        );
    }

    private Mono<Error> responseAnyError(Throwable throwable) {
        return Mono.just(Error.builder()
                .reason(throwable.getMessage())
                .code(TechnicalExceptionEnum.INTERNAL_SERVER_ERROR.getCode())
                .message(TechnicalExceptionEnum.INTERNAL_SERVER_ERROR.getMessage())
                .build()
        );
    }

}
