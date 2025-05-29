package com.app.NE.services.employee;

import com.app.NE.dto.requests.RegisterEmployeeDTO;
import com.app.NE.dto.responses.ApiResponse;
import org.springframework.data.domain.Pageable;

public interface IEmployeeService {
    ApiResponse getAllEmployees(Pageable pageable);
    ApiResponse getEmployeeByCode(String code);
    ApiResponse updateEmployee(String code, RegisterEmployeeDTO dto);
    ApiResponse disableEmployee(String code);
    ApiResponse enableEmployee(String code);
    ApiResponse getMyEmployees();
}
