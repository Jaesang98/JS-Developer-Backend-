package com.jsnam.JSDEV.util;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseUtil {

    // 성공
    public static <T> ResponseEntity<ApiResponse<T>> success(T data) {
        return ResponseEntity.ok(ApiResponse.success(data));
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(String message, T data) {
        return ResponseEntity.ok(ApiResponse.success(message, data));
    }

    // 실패
    public static <T> ResponseEntity<ApiResponse<T>> fail(String message) {
        return ResponseEntity.ok(ApiResponse.fail(message));
    }
}
