package co.com.nequi.api.commons;

import lombok.Builder;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class ResponseUtilTest {
    private TestClass testClass;

    @InjectMocks
    private Error errorResponse;

    @BeforeEach
    public void init() {
        testClass = TestClass.builder().name("test").build();
    }

    @Test
    void buildResponseOK() {
        StepVerifier.create(ResponseUtil.buildResponse(HttpStatus.OK, testClass))
                .expectNextMatches(res -> res.statusCode().is2xxSuccessful())
                .verifyComplete();
    }

    @Test
    void buildResponse() {
        StepVerifier.create(ResponseUtil.buildResponse(HttpStatus.CONFLICT, testClass))
                .expectNextMatches(res -> res.statusCode().is4xxClientError())
                .verifyComplete();
    }

    @Test
    void responseOK() {
        StepVerifier.create(ResponseUtil.responseOk("Response.."))
                .expectNextMatches(res -> res.statusCode().is2xxSuccessful())
                .verifyComplete();
    }

    @Test
    void buildResponseFail() {
        StepVerifier.create(ResponseUtil.responseFail(errorResponse))
                .expectNextMatches(res -> res.statusCode().is5xxServerError())
                .verifyComplete();
    }

    @Data
    @Builder
    static class TestClass {
        private String name;
    }
}
