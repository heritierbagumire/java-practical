package com.app.NE.serviceImpls.employee;

import com.app.NE.dto.requests.RegisterEmployeeDTO;
import com.app.NE.dto.responses.ApiResponse;
import com.app.NE.enums.EEmployeeStatus;
import com.app.NE.exceptions.NotFoundException;
import com.app.NE.models.Employee;
import com.app.NE.repositories.IEmployeeRepository;
import com.app.NE.serviceImpls.auth.AuthServiceImpl;
import com.app.NE.services.employee.IEmployeeService;
import com.app.NE.utils.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {
    private final IEmployeeRepository employeeRepository;
    private final AuthServiceImpl authServiceImpl;

    @Override
    public ApiResponse getAllEmployees(Pageable pageable) {
        Page<Employee> employees = employeeRepository.findAll(pageable);
        return ApiResponse.success(employees);
    }

    @Override
    public ApiResponse getEmployeeByCode(String code) {
        Employee employee = employeeRepository.findByCode(code).orElseThrow(() -> new NotFoundException("Employee not found"));
        return ApiResponse.success(employee);
    }

    @Override
    public ApiResponse updateEmployee(String code, RegisterEmployeeDTO dto) {
        // create the update employee
        Employee employee = employeeRepository.findByCode(code).orElseThrow(() -> new NotFoundException("Employee not found"));
        if(dto.getEmail() != null && !employeeRepository.existsByEmail(dto.getEmail())){
        employee.setEmail(dto.getEmail());
        }
        if(dto.getLastName() != null)
        {
            employee.setLastName(dto.getLastName());
        }

        if(dto.getFirstName() != null){
            employee.setFirstName(dto.getFirstName());
        }

        if(dto.getPassword() != null){
            employee.setPassword(Utility.hash(dto.getPassword()));
        }

        if(dto.getDateOfBirth() != null){
            employee.setDateOfBirth(dto.getDateOfBirth());
        }

        if(dto.getPhone()!=null){
            employee.setPhone(dto.getPhone());
        }

        employeeRepository.save(employee);
        return ApiResponse.success(employee);
    }

    @Override
    public ApiResponse disableEmployee(String code) {
       Employee employee = employeeRepository.findByCode(code).orElseThrow(() -> new NotFoundException("Employee not found"));
       employee.setStatus(EEmployeeStatus.DISABLED);
       employeeRepository.save(employee);
       return ApiResponse.success(employee);
    }

    @Override
    public ApiResponse enableEmployee(String code) {
        Employee employee = employeeRepository.findByCode(code).orElseThrow(() -> new NotFoundException("Employee not found"));
        employee.setStatus(EEmployeeStatus.ACTIVE);
        employeeRepository.save(employee);
        return ApiResponse.success(employee);
    }

    @Override
    public ApiResponse getMyEmployees() {
        List<Employee> employees = employeeRepository.findAllByInstitution(authServiceImpl.getPrincipal().getInstitution());
        return ApiResponse.success(employees);
    }
}
