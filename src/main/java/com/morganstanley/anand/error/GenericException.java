package com.morganstanley.anand.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class GenericException {
    private String reason;
    private int httpCode;
}
