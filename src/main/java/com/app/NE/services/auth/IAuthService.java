package com.app.NE.services.auth;

import com.app.NE.dto.requests.LoginDTO;
import com.app.NE.dto.requests.RegisterDTO;
import com.app.NE.dto.responses.ApiResponse;
import com.app.NE.models.User;

public interface IAuthService {
    ApiResponse login(LoginDTO dto);
    ApiResponse register(RegisterDTO dto);
    User getPrincipal();
    ApiResponse getLoggedInUser();
}
