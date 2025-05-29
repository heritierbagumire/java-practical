package com.app.NE.services.meter;

import com.app.NE.dto.requests.CreateMeterDTO;
import com.app.NE.dto.requests.UpdateMeterDTO;
import com.app.NE.dto.responses.ApiResponse;

import java.util.UUID;

public interface IMeterService {
    ApiResponse create(CreateMeterDTO dto);
    ApiResponse getMyMeters();
    ApiResponse getAllMeters();
    ApiResponse getMeterById(UUID id);
    ApiResponse updateMeter(UUID id, UpdateMeterDTO dto);
    ApiResponse deleteMeter(UUID id);
}
