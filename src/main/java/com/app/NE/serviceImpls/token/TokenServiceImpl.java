package com.app.NE.serviceImpls.token;

import com.app.NE.dto.requests.PurchaseTokenDTO;
import com.app.NE.dto.responses.ApiResponse;
import com.app.NE.enums.ETokenStatus;
import com.app.NE.exceptions.BadRequestException;
import com.app.NE.exceptions.NotFoundException;
import com.app.NE.models.Meter;
import com.app.NE.models.PurchasedToken;
import com.app.NE.repositories.IMeterRepository;
import com.app.NE.repositories.IPurchasedTokenRepository;
import com.app.NE.services.token.ITokenService;
import com.app.NE.utils.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements ITokenService {
    private final IPurchasedTokenRepository purchasedTokenRepository;
    private final IMeterRepository meterRepository;


    @Override
    public ApiResponse purchase(PurchaseTokenDTO dto) {
        if(dto.getAmount() < 100){
            throw new BadRequestException("Amount must be greater than 100");
        }

        String meterNumStr = String.valueOf(dto.getMeterNumber());
        if (!meterNumStr.matches("^\\d{6}$")) {
            throw new BadRequestException("Meter number must be exactly 6 digits!");
        }

        // get the amount and generate the token and the token will be containing the days of the token
        int days = dto.getAmount() / 100;
        if (days > 365 * 5) {
            throw new BadRequestException("You can't purchase electricity for more than 5 years! 😮");
        }

        String rawToken = Utility.generateToken();

        PurchasedToken token = new PurchasedToken();
                token.setMeterNumber(dto.getMeterNumber());
                token.setToken(rawToken);
                token.setStatus(ETokenStatus.NEW);
                token.setTokenValueDays(days);
                token.setPurchasedDate(LocalDateTime.now());
                token.setAmount(dto.getAmount());

        purchasedTokenRepository.save(token);

        // Format the token for readability
        String formattedToken = Utility.formatToken(rawToken);

        return new ApiResponse(formattedToken, "Token purchased successfully for " + days + " day(s)");
    }

    @Override
    public ApiResponse validateToken(String token) {
        PurchasedToken purchasedToken = purchasedTokenRepository.findByTokenAndStatus(token, ETokenStatus.NEW)
                .orElseThrow(() -> new NotFoundException("Token not found"));

        purchasedToken.setStatus(ETokenStatus.USED);
        purchasedTokenRepository.save(purchasedToken);

        return new ApiResponse(purchasedToken.getTokenValueDays(), "Token is valid Lights for " + purchasedToken.getTokenValueDays() + " day(s)");
    }

    @Override
    public ApiResponse getTokensByMeter(UUID meterId) {
        Meter meter = meterRepository.findById(meterId)
                .orElseThrow(() -> new NotFoundException("Meter not found"));

        List<PurchasedToken> tokens = purchasedTokenRepository.findByMeterNumber(meter.getMeterNumber());

        List<String> formattedTokens = tokens.stream()
                .map(t -> Utility.formatToken(t.getToken()) + " (" + t.getTokenValueDays() + " days)")
                .toList();

        return new ApiResponse(formattedTokens, "Tokens for this meter");
    }

    @Override
    public ApiResponse getToken(UUID id) {
        PurchasedToken token = purchasedTokenRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Token not found"));

        return new ApiResponse(
                Utility.formatToken(token.getToken()),
                "Token for meter " + token.getMeterNumber()
        );
    }
}
