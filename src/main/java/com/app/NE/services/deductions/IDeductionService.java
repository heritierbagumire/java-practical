package com.app.NE.services.deductions;

import com.app.NE.dto.requests.CreateDeductionDTO;
import com.app.NE.dto.responses.ApiResponse;
import com.app.NE.models.Deduction;

import java.util.UUID;

public interface IDeductionService {
    ApiResponse createDeduction(CreateDeductionDTO dto);
    ApiResponse getALlDeductions();
    ApiResponse getDeductionById(UUID id);
    ApiResponse getDeductionByCode(String code);
    ApiResponse updateDeduction(UUID id, CreateDeductionDTO dto);
    ApiResponse deleteDeduction(UUID id);
}
