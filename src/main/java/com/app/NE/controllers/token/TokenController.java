package com.app.NE.controllers.token;

import com.app.NE.dto.requests.PurchaseTokenDTO;
import com.app.NE.dto.responses.ApiResponse;
import com.app.NE.services.token.ITokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/token")
public class TokenController {
    private final ITokenService tokenService;

    @PostMapping("/purchase")
    public ResponseEntity<ApiResponse> purchaseToken(@RequestBody @Valid PurchaseTokenDTO dto) {
        ApiResponse response = tokenService.purchase(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/validate/{token}")
    public ResponseEntity<ApiResponse> validateToken(@PathVariable String token) {
        ApiResponse response = tokenService.validateToken(token);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/meter/{meterId}")
    public ResponseEntity<ApiResponse> getTokensByMeter(@PathVariable UUID meterId) {
        ApiResponse response = tokenService.getTokensByMeter(meterId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getTokenById(@PathVariable UUID id) {
        ApiResponse response = tokenService.getToken(id);
        return ResponseEntity.ok(response);
    }
}
