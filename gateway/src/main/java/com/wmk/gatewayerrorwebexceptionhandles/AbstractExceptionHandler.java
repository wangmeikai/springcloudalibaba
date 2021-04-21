package com.wmk.gatewayerrorwebexceptionhandles;

import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/21
 * @TIME: 19:38
 **/
public abstract class AbstractExceptionHandler {

    private static final String DEFAULT_ERROR_CODE = "520";

    protected String formatMessage(Throwable ex) {
        String errorMessage;
        if (ex instanceof NotFoundException) {
            errorMessage = ((NotFoundException) ex).getReason();
        }
        else if (ex instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) ex;
            errorMessage = responseStatusException.getMessage();
        } else {
            errorMessage = ex.toString();
        }
        return errorMessage;
    }

    protected Map<String, Object> buildErrorMap(String errorMessage) {
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("isSuccess", false);
        resMap.put("code", DEFAULT_ERROR_CODE);
        resMap.put("message", errorMessage);
        resMap.put("data", null);
        return resMap;
    }

}
