package com.app.NE.service;

import com.app.NE.dto.EmploymentRequest;
import com.app.NE.models.Employee;
import com.app.NE.models.Employment;
//import com.app.NE.model.EmploymentStatus;
//import com.app.NE.repository.EmployeeRepository;
//import com.app.NE.repository.EmploymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeService {

//    private final EmployeeRepository employeeRepository;
//    private final EmploymentRepository employmentRepository;
//
//    public List<Employee> getAllEmployees() {
//        return employeeRepository.findAll();
//    }
//
//    public Employee getEmployee(String code) {
//        return employeeRepository.findById(code)
//                .orElseThrow(() -> new RuntimeException("Employee not found"));
//    }
//
//    @Transactional
//    public Employment addEmployment(EmploymentRequest request) {
//        Employee employee = getEmployee(request.getEmployeeCode());
//
//        Employment employment = Employment.builder()
//                .code(UUID.randomUUID().toString())
//                .employee(employee)
//                .department(request.getDepartment())
//                .position(request.getPosition())
//                .baseSalary(request.getBaseSalary())
//                .status(EmploymentStatus.ACTIVE)
//                .joiningDate(request.getJoiningDate())
//                .build();
//
//        return employmentRepository.save(employment);
//    }
//
//    @Transactional
//    public Employment updateEmployment(String code, EmploymentRequest request) {
//        Employment employment = employmentRepository.findById(code)
//                .orElseThrow(() -> new RuntimeException("Employment not found"));
//
//        employment.setDepartment(request.getDepartment());
//        employment.setPosition(request.getPosition());
//        employment.setBaseSalary(request.getBaseSalary());
//        employment.setJoiningDate(request.getJoiningDate());
//
//        return employmentRepository.save(employment);
//    }
}