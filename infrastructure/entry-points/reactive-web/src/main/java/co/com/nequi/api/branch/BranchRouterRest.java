package co.com.nequi.api.branch;

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
public class BranchRouterRest {
    private final ApiProperties apiProperties;

    @Bean
    public RouterFunction<ServerResponse> routerFunctionBranch(HandlerBranch handlerBranch) {
        return route(POST(apiProperties.getBranch()), handlerBranch::create)
                .andRoute(PUT(apiProperties.getBranch()), handlerBranch::update)
                .and(route(GET(apiProperties.getBranch()+"/largest-stock"), handlerBranch::find))
                .and(route(GET(apiProperties.getBranch()), handlerBranch::findAll));
    }
}
