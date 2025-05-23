package co.com.nequi.model.exceptions;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TechnicalExceptionEnum {

    INTERNAL_SERVER_ERROR("FCT0001", "Internal server error", "500");

    private final String code;
    private final String message;
    private final String httpStatus;


}
