package co.com.nequi.api.franchise;

import co.com.nequi.api.ApiProperties;
import co.com.nequi.api.product.HandlerProduct;
import co.com.nequi.api.product.ProductRouterRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class FranchiseRouterRestTest {

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
        HandlerFranchise mockHandler = mock(HandlerFranchise.class);
        FranchiseRouterRest routerRest = new FranchiseRouterRest(properties);
        RouterFunction<ServerResponse> routerFunction = routerRest.routerFunctionFranchise(mockHandler);
        assertNotNull(routerFunction);
    }
}
