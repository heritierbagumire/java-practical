package com.app.NE.controllers.meter;

import com.app.NE.dto.requests.CreateMeterDTO;
import com.app.NE.dto.requests.UpdateMeterDTO;
import com.app.NE.dto.responses.ApiResponse;
import com.app.NE.services.meter.IMeterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/meter")
public class MeterController {
    private final IMeterService meterService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> createMeter(@Valid @RequestBody CreateMeterDTO dto){
        ApiResponse response = meterService.create(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> update(@PathVariable("id")UUID id, @Valid @RequestBody UpdateMeterDTO dto){
        ApiResponse response = meterService.updateMeter(id, dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all/mine")
    @PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
    public ResponseEntity<ApiResponse> getMyMeters(){
        ApiResponse response = meterService.getMyMeters();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> getAllMeters(){
        ApiResponse response = meterService.getAllMeters();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<ApiResponse> getMeter(@PathVariable("id")UUID id){
        ApiResponse response = meterService.getMeterById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse> deleteMeter(@PathVariable("id")UUID id){
        ApiResponse response = meterService.deleteMeter(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
