package co.com.nequi.api.product;

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
public class ProductRouterRest {
    private final ApiProperties apiProperties;

    @Bean
    public RouterFunction<ServerResponse> routerFunctionProduct(HandlerProduct handlerProduct) {
        return route(POST(apiProperties.getProduct()), handlerProduct::create)
                .andRoute(PUT(apiProperties.getProduct()), handlerProduct::update)
                .andRoute(DELETE(apiProperties.getProduct()), handlerProduct::delete);
    }
}
