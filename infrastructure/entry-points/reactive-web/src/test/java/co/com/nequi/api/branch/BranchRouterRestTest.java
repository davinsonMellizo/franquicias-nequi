package co.com.nequi.api.branch;

import co.com.nequi.api.ApiProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class BranchRouterRestTest {

    private ApiProperties properties;

    @BeforeEach
    void setUp() {

        properties = new ApiProperties();
        properties.setBranch("/branch");
        properties.setFranchise("/franchise");
        properties.setProduct("/product");
    }

    @Test
    void testRouterFunction() {
        HandlerBranch mockHandler = mock(HandlerBranch.class);
        BranchRouterRest routerRest = new BranchRouterRest(properties);
        RouterFunction<ServerResponse> routerFunction = routerRest.routerFunctionBranch(mockHandler);
        assertNotNull(routerFunction);
    }
}
