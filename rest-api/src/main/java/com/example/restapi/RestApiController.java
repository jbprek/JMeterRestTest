package com.example.restapi;

import lombok.Builder;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.locks.LockSupport;

@RestController
public class RestApiController {

    private static Random rng = new Random();

//    @Value
//    public static class PostPayload {
//        String bodyString;
//        LocalDate bodyDate;
//        boolean bodyBoolean;
//        double bodyDouble;
//    }
//
//
//    @Value
//    public static class ResponsePayload {
//        String headerAuthorize;
//        String headerTraceId;
//        String queryParamDomain;
//        String queryParamDetail;
//        String bodyString;
//        LocalDate bodyDate;
//        boolean bodyBoolean;
//        double bodyDouble;
//    }


    @Value(staticConstructor = "of")
    public static class ResponseGetPayload {
        String headerAuthorize;
        String headerTraceId;
        String queryParamDomain;
        String queryParamDetail;
        long waitTime;

    }

    @GetMapping
    public ResponseEntity<ResponseGetPayload> get(
            @RequestHeader("Authorization") String headerAuthorize,
            @RequestHeader("trace-id") String headerTraceId,
            @RequestParam("domain") String queryParamDomain,
            @RequestParam("detail") String queryParamDetail) {

        long waitTime = (long) (rng.nextDouble() * 100);
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(ResponseGetPayload.of(
                headerAuthorize,
                headerTraceId,
                queryParamDomain,
                queryParamDetail,
                waitTime
        ));
    }


}
