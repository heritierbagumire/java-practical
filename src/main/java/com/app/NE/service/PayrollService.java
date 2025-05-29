package com.app.NE.service;

import com.app.NE.enums.ERole;
import com.app.NE.models.*;
//import com.app.NE.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PayrollService {

//    private final EmployeeRepository employeeRepository;
//    private final EmploymentRepository employmentRepository;
//    private final DeductionRepository deductionRepository;
//    private final PaySlipRepository paySlipRepository;
//    private final MessageRepository messageRepository;
//
//    @Transactional
//    public List<PaySlip> processPayroll(Integer month, Integer year) {
//        List<Employment> activeEmployments = employmentRepository.findByStatus(EmploymentStatus.ACTIVE);
//        List<Deduction> deductions = deductionRepository.findAll();
//
//        return activeEmployments.stream()
//                .map(employment -> createPaySlip(employment, deductions, month, year))
//                .collect(Collectors.toList());
//    }
//
//    private PaySlip createPaySlip(Employment employment, List<Deduction> deductions, Integer month, Integer year) {
//        BigDecimal baseSalary = employment.getBaseSalary();
//        BigDecimal totalDeductionAmount = calculateTotalDeductions(baseSalary, deductions);
//        BigDecimal netSalary = baseSalary.subtract(totalDeductionAmount);
//
//        return PaySlip.builder()
//                .employee(employment.getEmployee())
//                .grossSalary(baseSalary)
//                .netSalary(netSalary)
//                .month(month)
//                .year(year)
//                .status(ERole.PaySlipStatus.PENDING)
//                .build();
//    }
//
//    private BigDecimal calculateTotalDeductions(BigDecimal baseSalary, List<Deduction> deductions) {
//        return deductions.stream()
//                .map(deduction -> baseSalary.multiply(deduction.getPercentage())
//                        .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP))
//                .reduce(BigDecimal.ZERO, BigDecimal::add);
//    }
//
//    public List<PaySlip> getAllPaySlips(Integer month, Integer year) {
//        return paySlipRepository.findByMonthAndYear(month, year);
//    }
//
//    public List<PaySlip> getEmployeePaySlips(String employeeCode, Integer month, Integer year) {
//        Employee employee = employeeRepository.findById(employeeCode)
//                .orElseThrow(() -> new RuntimeException("Employee not found"));
//        return paySlipRepository.findByEmployeeAndMonthAndYear(employee, month, year);
//    }
//
//    @Transactional
//    public void approvePayroll(Integer month, Integer year) {
//        List<PaySlip> paySlips = paySlipRepository.findByMonthAndYear(month, year);
//        paySlips.forEach(paySlip -> {
//            paySlip.setStatus(ERole.PaySlipStatus.PAID);
//            createPaymentMessage(paySlip);
//        });
//        paySlipRepository.saveAll(paySlips);
//    }
//
//    private void createPaymentMessage(PaySlip paySlip) {
//        Message message = Message.builder()
//                .employee(paySlip.getEmployee())
//                .message("Dear " + paySlip.getEmployee().getFirstname() + ", your salary payment for " +
//                        paySlip.getMonth() + "/" + paySlip.getYear() + " has been processed.")
//                .month(paySlip.getMonth())
//                .year(paySlip.getYear())
//                .sentAt(LocalDateTime.now())
//                .sent(false)
//                .build();
//        messageRepository.save(message);
//    }
}