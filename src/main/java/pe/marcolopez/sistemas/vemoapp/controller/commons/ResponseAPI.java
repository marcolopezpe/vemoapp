package pe.marcolopez.sistemas.vemoapp.controller.commons;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseAPI {


    private String apiVersion;
    private ResponseStatus status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object result;
}
