package com.app.NE.services.employeement;

import com.app.NE.dto.requests.CreateEmployeementDTO;
import com.app.NE.dto.responses.ApiResponse;

import java.util.UUID;

public interface IEmployeementService {
    ApiResponse createEmployeement(CreateEmployeementDTO dto);
    ApiResponse getEmployeementById(UUID id);
    ApiResponse getEmployeementByEmployeeCode(String code);
    ApiResponse updateEmployeement(String code, CreateEmployeementDTO dto);
    ApiResponse deactivateEmployeement(String code);
    ApiResponse activateEmployeement(String code);
}
