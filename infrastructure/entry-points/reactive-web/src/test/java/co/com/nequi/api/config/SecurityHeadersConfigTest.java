package co.com.nequi.api.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SecurityHeadersConfigTest {
    @InjectMocks
    private SecurityHeadersConfig config;
    @Mock
    private ServerWebExchange exchange;
    @Mock
    ServerHttpResponse response;
    @Mock
    private WebFilterChain chain;
    @Test
    void filter(){
        Mockito.when(exchange.getResponse()).thenReturn(response);
        Mockito.when(response.getHeaders()).thenReturn(new HttpHeaders());
        when(chain.filter(any())).thenReturn(Mono.empty());

        StepVerifier.create(config.filter(exchange, chain))
                .verifyComplete();
    }
}
