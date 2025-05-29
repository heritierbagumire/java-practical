package com.app.NE.repositories;

import com.app.NE.enums.ETokenStatus;
import com.app.NE.models.PurchasedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IPurchasedTokenRepository extends JpaRepository<PurchasedToken, UUID> {
    Optional<PurchasedToken> findByTokenAndStatus(String token, ETokenStatus eTokenStatus);

    List<PurchasedToken> findByMeterNumber(int meterNumber);

    List<PurchasedToken> findByStatus(ETokenStatus eTokenStatus);

    @Query("SELECT pt FROM PurchasedToken pt WHERE pt.status = com.app.NE.enums.ETokenStatus.NEW AND " +
            "FUNCTION('DATE_ADD', pt.purchasedDate, pt.tokenValueDays, 'DAY') BETWEEN " +
            "FUNCTION('DATE_ADD', CURRENT_TIMESTAMP, 5, 'HOUR') AND " +
            "FUNCTION('DATE_ADD', CURRENT_TIMESTAMP, 6, 'HOUR')")
    List<PurchasedToken> findTokensExpiringInFiveHours();
}
