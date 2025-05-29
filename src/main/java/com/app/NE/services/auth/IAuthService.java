package com.app.NE.services.auth;

import com.app.NE.dto.requests.LoginDTO;
import com.app.NE.dto.requests.RegisterDTO;
import com.app.NE.dto.requests.RegisterEmployeeDTO;
import com.app.NE.dto.responses.ApiResponse;
import com.app.NE.models.User;

public interface IAuthService {
    ApiResponse login(LoginDTO dto);
    ApiResponse register(RegisterDTO dto);
    ApiResponse registerEmployee(RegisterEmployeeDTO dto);
    User getPrincipal();
    ApiResponse getLoggedInUser();
}
