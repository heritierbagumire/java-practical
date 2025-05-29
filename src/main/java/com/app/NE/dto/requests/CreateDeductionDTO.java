package com.app.NE.dto.requests;

import com.app.NE.enums.EDeductionName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDeductionDTO {
    private EDeductionName name;
    private BigDecimal percentage;
}
