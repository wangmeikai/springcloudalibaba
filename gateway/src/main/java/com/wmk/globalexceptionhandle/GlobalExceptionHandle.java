package com.wmk.globalexceptionhandle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wmk.util.ResultCommon;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/23
 * @TIME: 17:18
 **/
@Component     // 全局异常处理
public class GlobalExceptionHandle implements ErrorWebExceptionHandler {

    private final ObjectMapper objectMapper;

    public GlobalExceptionHandle(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {

        System.out.println("===================进入GlobalExceptionHandle");
        ServerHttpResponse response = exchange.getResponse();

        if (response.isCommitted()) {
            return Mono.error(ex);
        }

        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        if (ex instanceof ResponseStatusException) {
            response.setStatusCode(((ResponseStatusException) ex).getStatus());
        }

        return response
                .writeWith(Mono.fromSupplier(() -> {
                    DataBufferFactory bufferFactory = response.bufferFactory();
                    try {
                        return bufferFactory.wrap(objectMapper.writeValueAsBytes(ResultCommon.failed(ex.getMessage())));
                    } catch (JsonProcessingException e) {
                        return bufferFactory.wrap(new byte[0]);
                    }
                }));

//        return response
//                .writeWith(Mono.fromSupplier(new Supplier<DataBuffer>() {
//                    @Override
//                    public DataBuffer get() {
//                        Map<String,String> map = new HashMap<>();
//                        map.put("code","1314");
//                        map.put("message","网关异常");
//                        try {
//                            return response.bufferFactory().wrap(objectMapper.writeValueAsBytes(map));
//                        } catch (JsonProcessingException e) {
//                            return response.bufferFactory().wrap("网关异常信息解析异常".getBytes());
//                        }
//                    }
//                }));
    }
}
