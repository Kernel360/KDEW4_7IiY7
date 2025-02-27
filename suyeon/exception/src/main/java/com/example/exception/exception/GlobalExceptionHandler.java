package com.example.exception.exception;


import com.example.exception.model.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MAX_VALUE)  //순서를 정해줄 수 O, exception 두개 중 뭘 먼저 할 지 정해줄 수 있음. 여기선 마지막에 이 클래스 실행
//RestApiException에서 감지하지 못한 오류들을 여기서 해결하겠다
public class GlobalExceptionHandler {

    public ResponseEntity<Api> exception(
            Exception e
    ) {
        log.error("", e);

        var response = Api.builder()
                .resultCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .resultMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }


}
