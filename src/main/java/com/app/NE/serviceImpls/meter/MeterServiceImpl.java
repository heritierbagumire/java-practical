package com.app.NE.serviceImpls.meter;

import com.app.NE.dto.requests.CreateMeterDTO;
import com.app.NE.dto.requests.UpdateMeterDTO;
import com.app.NE.dto.responses.ApiResponse;
import com.app.NE.exceptions.BadRequestException;
import com.app.NE.exceptions.NotFoundException;
import com.app.NE.models.Meter;
import com.app.NE.models.User;
import com.app.NE.repositories.IMeterRepository;
import com.app.NE.repositories.IUserRepository;
import com.app.NE.services.auth.IAuthService;
import com.app.NE.services.meter.IMeterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MeterServiceImpl implements IMeterService {
    private final IUserRepository userRepository;
    private final IMeterRepository meterRepository;
    private final IAuthService authService;


    @Override
    public ApiResponse create(CreateMeterDTO dto) {
        if(dto.getCustomerId() == null){
            throw new BadRequestException("Customer id must be provided.");
        }
        String meterNumStr = String.valueOf(dto.getMeterNumber());
        if (!meterNumStr.matches("^\\d{6}$")) {
            throw new BadRequestException("Meter number must be exactly 6 digits!");
        }
        // create the meter to a certain user
        if(meterRepository.existsByMeterNumber(dto.getMeterNumber())){
            throw new BadRequestException(String.format("Meter number %s already exists.", dto.getMeterNumber()));
        }

        // get the user
        if(!userRepository.existsById(dto.getCustomerId())){
            throw new NotFoundException(String.format("Customer id %s does not exist.", dto.getCustomerId()));
        }

        // create the meter and push it in the user
        User user = userRepository.findById(dto.getCustomerId()).get();
        Meter meter = new Meter();
        meter.setMeterNumber(dto.getMeterNumber());
        meter.setUser(user);
        Meter m = meterRepository.save(meter);
        if(user.getMeters() == null){
            user.setMeters(new ArrayList<>());
        }
        user.getMeters().add(meter);
        userRepository.save(user);
        return new ApiResponse(m, "Meter created successfully.");
    }

    @Override
    public ApiResponse getMyMeters() {
        User user = authService.getPrincipal();
        return new ApiResponse(meterRepository.findAllByUser(user), null);
    }

    @Override
    public ApiResponse getAllMeters() {
        return new ApiResponse(meterRepository.findAll(), null);
    }

    @Override
    public ApiResponse getMeterById(UUID id) {
        return new ApiResponse(meterRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Meter %s not found.", id))), null);
    }

    @Override
    public ApiResponse updateMeter(UUID id, UpdateMeterDTO dto) {
        Meter meter = meterRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Meter %s not found.", id)));

        if (dto.getMeterNumber() != 0) {
            String meterNumStr = String.valueOf(dto.getMeterNumber());
            if (!meterNumStr.matches("^\\d{6}$")) {
                throw new BadRequestException("Meter number must be exactly 6 digits!");
            }

            if (meterRepository.existsByMeterNumber(dto.getMeterNumber()) &&
                    meter.getMeterNumber() != dto.getMeterNumber()) {
                throw new BadRequestException("Meter number already in use! 🚫");
            }

            meter.setMeterNumber(dto.getMeterNumber());
        }

        Meter m = meterRepository.save(meter);

        return new ApiResponse<>(m, "Meter updated successfully ✅");
    }


    @Override
    public ApiResponse deleteMeter(UUID id) {
        if(!meterRepository.existsById(id))throw new NotFoundException(String.format("Meter %s not found.", id));
        meterRepository.deleteById(id);
        return new ApiResponse(null, String.format("Meter %s deleted successfully.", id));
    }
}
