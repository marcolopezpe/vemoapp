package pe.marcolopez.sistemas.vemoapp.controller.commons;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import pe.marcolopez.sistemas.vemoapp.controller.enums.ResponseEnum;

import java.util.Date;

@Data
@Builder
public class ResponseStatus {

    @Builder.Default
    private Date dateTime = new Date();
    private int status;
    private String error;
    private ResponseEnum code;
    private Object message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object detail;
}
