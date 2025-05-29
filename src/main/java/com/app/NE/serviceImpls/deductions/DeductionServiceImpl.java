package com.app.NE.serviceImpls.deductions;

import com.app.NE.dto.requests.CreateDeductionDTO;
import com.app.NE.dto.responses.ApiResponse;
import com.app.NE.exceptions.BadRequestException;
import com.app.NE.models.Deduction;
import com.app.NE.repositories.IDeductionRepository;
import com.app.NE.services.deductions.IDeductionService;
import com.app.NE.utils.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeductionServiceImpl implements IDeductionService {
    private final IDeductionRepository repository;
    @Override
    public ApiResponse createDeduction(CreateDeductionDTO dto) {
        // check if it is unique
        if(repository.existsByDeductionName(dto.getName())){
            throw new BadRequestException("Deduction already exists");
        }

        // create the deduction
        Deduction deduction = new Deduction();
        deduction.setDeductionName(dto.getName());
        deduction.setPercentage(dto.getPercentage());
        deduction.setCode(Utility.generateDeductionCode(dto.getName()));

        deduction = repository.save(deduction);
        return ApiResponse.success(deduction);
    }

    @Override
    public ApiResponse getALlDeductions() {
        List<Deduction> deductions = repository.findAll();
        return ApiResponse.success(deductions);
    }

    @Override
    public ApiResponse getDeductionByCode(String code) {
        Deduction deduction = repository.findByCode(code).orElseThrow(() -> new BadRequestException("Deduction does not exist"));
        return ApiResponse.success(deduction);
    }

    @Override
    public ApiResponse getDeductionById(UUID id) {
        Deduction deduction = repository.findById(id).orElseThrow(() -> new BadRequestException("Deduction does not exist"));
        return ApiResponse.success(deduction);
    }

    @Override
    public ApiResponse updateDeduction(UUID id, CreateDeductionDTO dto) {
        Deduction deduction = repository.findById(id).orElseThrow(() -> new BadRequestException("Deduction does not exist"));
        if(dto.getName() != null){
            deduction.setDeductionName(dto.getName());
        }

        if(dto.getPercentage() != null){
            deduction.setPercentage(dto.getPercentage());
        }

        repository.save(deduction);
        return ApiResponse.success(deduction);
    }

    @Override
    public ApiResponse deleteDeduction(UUID id) {
        Deduction deduction = repository.findById(id).orElseThrow(() -> new BadRequestException("Deduction does not exist"));
        repository.delete(deduction);
        return new ApiResponse(true, "Deduction deleted successfully");
    }
}
