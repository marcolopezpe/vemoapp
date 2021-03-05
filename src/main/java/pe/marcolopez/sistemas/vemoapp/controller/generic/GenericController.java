package pe.marcolopez.sistemas.vemoapp.controller.generic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import pe.marcolopez.sistemas.vemoapp.controller.commons.ResponseAPI;
import pe.marcolopez.sistemas.vemoapp.controller.commons.ResponseStatus;
import pe.marcolopez.sistemas.vemoapp.controller.constants.ResponseMessage;
import pe.marcolopez.sistemas.vemoapp.controller.enums.ResponseEnum;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class GenericController {

    @Value("${api.version}")
    private String apiVersion;

    protected String formatMapMessage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream().map(err -> {
            Map<String, String> error = new HashMap<>();
            error.put(err.getField(), err.getDefaultMessage());
            return error;
        }).collect(Collectors.toList());
        return errors.toString();
    }

    protected List<Map<String, String>> formatMapMessageList(BindingResult result) {
        return result.getFieldErrors().stream().map(err -> {
            Map<String, String> error = new HashMap<>();
            error.put(err.getField(), err.getDefaultMessage());
            return error;
        }).collect(Collectors.toList());
    }

    protected ResponseEntity<ResponseAPI> getBadRequest(BindingResult result) {
        ResponseAPI responseAPI = ResponseAPI.builder()
                .apiVersion(apiVersion)
                .status(ResponseStatus.builder()
                        .code(ResponseEnum.WARNING)
                        .message(ResponseMessage.MESSAGE_REGISTER_EROR)
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error(HttpStatus.BAD_REQUEST.name())
                        .detail(formatMapMessageList(result))
                        .build())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseAPI);
    }

    protected ResponseEntity<ResponseAPI> getBadRequest(String detail) {
        ResponseAPI responseAPI = ResponseAPI.builder()
                .apiVersion(apiVersion)
                .status(ResponseStatus.builder()
                        .code(ResponseEnum.WARNING)
                        .message(ResponseMessage.MESSAGE_GENERIC_WARNING)
                        .status(HttpStatus.BAD_REQUEST.value())
                        .error(HttpStatus.BAD_REQUEST.name())
                        .detail(detail)
                        .build())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseAPI);
    }

    protected ResponseEntity<ResponseAPI> getBadIDRequest() {
        return getBadRequest(ResponseMessage.MESSAGE_GENERIC_WARNING_ID);
    }

    protected ResponseEntity<ResponseAPI> getNotFoundRequest() {
        ResponseAPI responseAPI = ResponseAPI.builder()
                .apiVersion(apiVersion)
                .status(ResponseStatus.builder()
                        .code(ResponseEnum.WARNING)
                        .message(ResponseMessage.MESSAGE_REQUEST_NO_CONTENT)
                        .status(HttpStatus.NOT_FOUND.value())
                        .error(HttpStatus.NOT_FOUND.name())
                        .build())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseAPI);
    }

    protected ResponseEntity<ResponseAPI> getNoContentRequest() {
        ResponseAPI responseAPI = ResponseAPI.builder()
                .apiVersion(apiVersion)
                .status(ResponseStatus.builder()
                        .code(ResponseEnum.WARNING)
                        .message(ResponseMessage.MESSAGE_REQUEST_NO_CONTENT)
                        .status(HttpStatus.NO_CONTENT.value())
                        .error(HttpStatus.NO_CONTENT.name())
                        .build())
                .build();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseAPI);
    }

    protected ResponseEntity<ResponseAPI> getCreatedRequest(Object o) {
        ResponseAPI responseAPI = ResponseAPI.builder()
                .apiVersion(apiVersion)
                .status(ResponseStatus.builder()
                        .code(ResponseEnum.SUCCESS)
                        .message(ResponseMessage.MESSAGE_REGISTER_SUCCESS)
                        .status(HttpStatus.CREATED.value())
                        .error(HttpStatus.CREATED.name())
                        .build())
                .result(o)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(responseAPI);
    }

    protected ResponseEntity<ResponseAPI> getOkRegiterRequest(Object o) {
        ResponseAPI responseAPI = ResponseAPI.builder()
                .apiVersion(apiVersion)
                .status(ResponseStatus.builder()
                        .code(ResponseEnum.SUCCESS)
                        .message(ResponseMessage.MESSAGE_REGISTER_SUCCESS)
                        .status(HttpStatus.OK.value())
                        .error(HttpStatus.OK.name())
                        .build())
                .result(o)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(responseAPI);
    }

    protected ResponseEntity<ResponseAPI> getSuccessRequest(Object o) {
        ResponseAPI responseAPI = ResponseAPI.builder()
                .apiVersion(apiVersion)
                .status(ResponseStatus.builder()
                        .code(ResponseEnum.SUCCESS)
                        .message(ResponseMessage.MESSAGE_REQUEST_SUCCESS)
                        .status(HttpStatus.OK.value())
                        .error(HttpStatus.OK.name())
                        .build())
                .result(o)
                .build();
        return ResponseEntity.ok(responseAPI);
    }

    protected ResponseEntity<ResponseAPI> getErrorRequest() {
        ResponseAPI responseAPI = ResponseAPI.builder()
                .apiVersion(apiVersion)
                .status(ResponseStatus.builder()
                        .code(ResponseEnum.ERROR)
                        .message(ResponseMessage.MESSAGE_GENERIC_ERROR)
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .error(HttpStatus.INTERNAL_SERVER_ERROR.name())
                        .build())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseAPI);
    }
}
