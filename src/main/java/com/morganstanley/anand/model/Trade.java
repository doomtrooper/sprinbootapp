package com.morganstanley.anand.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Table(name = "trade")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Trade {

    @Id
    private String tradeId;
    @NotEmpty(message = "Stock name NOT present.")
    private String stockName;
    @NotEmpty(message = "Broker code NOT present.")
    private String brokerCode;
    @NotEmpty(message = "Broker name NOT present.")
    private String brokerName;
    @Min(value = 0 , message = "Quantity should be greater than 0.")
    private int quantity;
    private Date tradeDate;
    private Date settlementDate;
    @Enumerated(EnumType.STRING)
    private Indicator indicator;
}
