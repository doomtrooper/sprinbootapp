package com.morganstanley.anand.model;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TradeInput {
    private String tradeId;
    @NotEmpty(message = "Stock name NOT present.")
    private String stockName;
    @NotEmpty(message = "Broker code NOT present.")
    private String brokerCode;
    @NotEmpty(message = "Broker name NOT present.")
    private String brokerName;
    @Min(value = 0 , message = "Quantity should be greater than 0.")
    private int quantity;
    private String tradeDate;
    private String settlementDate;
    @Enumerated(EnumType.STRING)
    private String indicator;
}
