package com.app.NE.security.handlers;

import com.app.NE.dto.responses.ApiResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ApiResponse<String> apiResponse = new ApiResponse<>(
                false,
                "You do not have permission to access this resource.",
                "Access Denied",
                LocalDateTime.now().toString(), null);

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");

        PrintWriter writer = response.getWriter();
        writer.write(apiResponse.toString());
        writer.flush();
    }
}
