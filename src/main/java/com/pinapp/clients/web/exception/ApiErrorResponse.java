package com.pinapp.clients.web.exception;

import java.time.LocalDateTime;
import java.util.Map;

public record ApiErrorResponse(
        int status,
        Map<String, String> message,
        String timestamp
) {
}
