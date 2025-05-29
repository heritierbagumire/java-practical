package com.app.NE.services.employee;

import com.app.NE.dto.requests.RegisterEmployeeDTO;
import com.app.NE.dto.responses.ApiResponse;
import com.app.NE.models.Employee;

public interface IEmployeeService {
    ApiResponse getAllEmployees();
    ApiResponse getEmployeeById(String code);
    ApiResponse updateEmployee(String code, RegisterEmployeeDTO dto);
    ApiResponse deleteEmployee(String code);
}
