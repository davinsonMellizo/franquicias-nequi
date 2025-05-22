package co.com.nequi.api.franchise;

import co.com.nequi.api.ApiProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class FranchiseRouterRest {
    private final ApiProperties apiProperties;

    @Bean
    public RouterFunction<ServerResponse> routerFunctionFranchise(HandlerFranchise handlerFranchise) {
        return route(POST(apiProperties.getFranchise()), handlerFranchise::create)
                .andRoute(PUT(apiProperties.getFranchise()), handlerFranchise::update);
    }
}
