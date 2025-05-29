package com.app.NE.controllers;

//import com.app.NE.dto.PayrollRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payroll")
@RequiredArgsConstructor
public class PayrollController {

//    private final PayrollService payrollService;
//
//    @PostMapping("/process")
//    @PreAuthorize("hasRole('MANAGER')")
//    public ResponseEntity<List<PaySlip>> processPayroll(@RequestBody PayrollRequest request) {
//        return ResponseEntity.ok(payrollService.processPayroll(request.getMonth(), request.getYear()));
//    }
//
//    @GetMapping("/slips")
//    @PreAuthorize("hasRole('MANAGER')")
//    public ResponseEntity<List<PaySlip>> getAllPaySlips(
//            @RequestParam Integer month,
//            @RequestParam Integer year) {
//        return ResponseEntity.ok(payrollService.getAllPaySlips(month, year));
//    }
//
//    @GetMapping("/slips/{employeeCode}")
//    @PreAuthorize("hasAnyRole('MANAGER', 'EMPLOYEE')")
//    public ResponseEntity<List<PaySlip>> getEmployeePaySlips(
//            @PathVariable String employeeCode,
//            @RequestParam Integer month,
//            @RequestParam Integer year) {
//        return ResponseEntity.ok(payrollService.getEmployeePaySlips(employeeCode, month, year));
//    }
//
//    @PostMapping("/approve")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<Void> approvePayroll(
//            @RequestParam Integer month,
//            @RequestParam Integer year) {
//        payrollService.approvePayroll(month, year);
//        return ResponseEntity.ok().build();
//    }
}