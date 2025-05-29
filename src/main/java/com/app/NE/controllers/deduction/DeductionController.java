package com.app.NE.controllers.deduction;

import com.app.NE.dto.requests.CreateDeductionDTO;
import com.app.NE.dto.requests.CreateEmployeementDTO;
import com.app.NE.dto.responses.ApiResponse;
import com.app.NE.services.deductions.IDeductionService;
import com.app.NE.services.employeement.IEmployeementService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/deduction")
@RequiredArgsConstructor
public class DeductionController {
    private final IDeductionService deductionService;

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> create(@Valid @RequestBody CreateDeductionDTO dto) {
        ApiResponse response = deductionService.createDeduction(dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAll() {
        ApiResponse response = deductionService.getALlDeductions();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable UUID id) {
        ApiResponse response = deductionService.getDeductionById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/by-code/{code}")
    public ResponseEntity<ApiResponse> getByCode(@PathVariable String code) {
        ApiResponse response = deductionService.getDeductionByCode(code);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable("id") UUID id, @RequestBody CreateDeductionDTO dto) {
        ApiResponse response = deductionService.updateDeduction(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable("id") UUID id) {
        ApiResponse response = deductionService.deleteDeduction(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}