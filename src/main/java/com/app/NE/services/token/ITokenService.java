package com.app.NE.services.token;

import com.app.NE.dto.requests.PurchaseTokenDTO;
import com.app.NE.dto.responses.ApiResponse;

import java.util.UUID;

public interface ITokenService {
    ApiResponse purchase(PurchaseTokenDTO dto);
    ApiResponse validateToken(String token);
    ApiResponse getTokensByMeter(UUID meterId);
    ApiResponse getToken(UUID id);
}
