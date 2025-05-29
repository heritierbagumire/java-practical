package com.app.NE.serviceImpls.employeement;

import com.app.NE.dto.requests.CreateEmployeementDTO;
import com.app.NE.dto.responses.ApiResponse;
import com.app.NE.enums.EEmployementStatus;
import com.app.NE.exceptions.NotFoundException;
import com.app.NE.models.Employee;
import com.app.NE.models.Employment;
import com.app.NE.repositories.IEmployeeRepository;
import com.app.NE.repositories.IEmployementRepository;
import com.app.NE.services.employeement.IEmployeementService;
import com.app.NE.utils.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeementServiceImpl implements IEmployeementService {
    private final IEmployeeRepository employeeRepository;
    private final IEmployementRepository employementRepository;
    @Override
    public ApiResponse createEmployeement(CreateEmployeementDTO dto) {
        // get the employee
        Employee employee = employeeRepository.findByCode(dto.getEmployeeCode()).orElseThrow(() -> new NotFoundException("Employee Not Found"));
        // create the employement
        Employment employment = new Employment();
        employment.setEmployee(employee);
        employment.setCode(Utility.generateEmployeementCode());
        employment.setDepartment(dto.getDepartment());
        employment.setBaseSalary(dto.getBaseSalary());
        employment.setPosition(dto.getPosition());
        employment.setJoiningDate(dto.getJoinDate());
        employment.setStatus(dto.getStatus());
        employementRepository.save(employment);
        return ApiResponse.success(employment);
    }

    @Override
    public ApiResponse getEmployeementById(UUID id) {
        Employment employment = employementRepository.findById(id).orElseThrow(() -> new NotFoundException("Employment not found."));
        return ApiResponse.success(employment);
    }

    @Override
    public ApiResponse getEmployeementByEmployeeCode(String code) {
        Employee employee = employeeRepository.findByCode(code).orElseThrow(() -> new NotFoundException("Employee not found."));
        Employment employment = employementRepository.findByEmployee_Code(employee.getCode()).orElseThrow(() -> new NotFoundException("Employment not found."));
        return ApiResponse.success(employment);
    }

    @Override
    public ApiResponse updateEmployeement(String code, CreateEmployeementDTO dto) {
        Employment employment = employementRepository.findByCode(code).orElseThrow(() -> new NotFoundException("Employment not found."));
        if(dto.getStatus() != null){
            employment.setStatus(dto.getStatus());
        }

        if(dto.getBaseSalary() != null){
            employment.setBaseSalary(dto.getBaseSalary());
        }

        if(dto.getPosition() != null){
            employment.setPosition(dto.getPosition());
        }

        if(dto.getDepartment() != null){
            employment.setDepartment(dto.getDepartment());
        }

        if(dto.getJoinDate() != null){
            employment.setJoiningDate(dto.getJoinDate());
        }

    employementRepository.save(employment);
        return ApiResponse.success(employment);
    }

    @Override
    public ApiResponse deactivateEmployeement(String code) {
        Employment employment = employementRepository.findByCode(code).orElseThrow(() -> new NotFoundException("Employment not found."));
        employment.setStatus(EEmployementStatus.INACTIVE);
        employementRepository.save(employment);
        return ApiResponse.success(employment);
    }

    @Override
    public ApiResponse activateEmployeement(String code) {
        Employment employment = employementRepository.findByCode(code).orElseThrow(() -> new NotFoundException("Employment not found."));
        employment.setStatus(EEmployementStatus.ACTIVE);
        employementRepository.save(employment);
        return ApiResponse.success(employment);
    }
}
