package com.example.authserver;

import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class AuthController {

    @Value(staticConstructor = "of")
    public static class InputPayload {
        String username;
        String password;
    }

    @Value(staticConstructor = "of")
    public static class OutputPayload {
        String authtoken;
    }

    @PostMapping
    public ResponseEntity<OutputPayload> authorize(@RequestBody InputPayload inputPayload) {
        if (StringUtils.hasLength(inputPayload.getUsername()) &&
                StringUtils.hasLength(inputPayload.getPassword())){
            return ResponseEntity.ok(OutputPayload.of(UUID.randomUUID().toString()));
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

}
